/*
 * This software is licensed under the Apache 2 license, quoted below.
 *
 * Copyright 2018 Astraea. Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     [http://www.apache.org/licenses/LICENSE-2.0]
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 *
 */
import java.nio.file.Files

import com.lightbend.paradox.markdown._
import com.lightbend.paradox.sbt.ParadoxPlugin
import com.lightbend.paradox.sbt.ParadoxPlugin.autoImport._
import sbt._
import sbt.Keys._
import org.pegdown.{Printer, ToHtmlSerializer}
import org.pegdown.ast.{Node ⇒ PDNode, _}
import scala.sys.process._
import scala.collection.JavaConverters._
/**
 *
 *
 * @since 6/27/18
 */
object JupyterDocsPlugin extends AutoPlugin {
  override def trigger = NoTrigger
  override def requires = ParadoxPlugin

  // TODO: remove after this PR is merged and released:
  // https://github.com/lightbend/paradox/pull/219
  trait CopiedSourceDirective { this: Directive =>
    def page: Page

    protected def resolvedSource(node: DirectiveNode, page: Page): String = {
      def ref(key: String) =
        referenceMap.get(key.filterNot(_.isWhitespace).toLowerCase).map(_.getUrl).getOrElse(
          throw new RefDirective.LinkException(s"Undefined reference key [$key] in [${page.path}]"))
      node.source match {
        case x: DirectiveNode.Source.Direct => x.value
        case x: DirectiveNode.Source.Ref    => ref(x.value)
        case DirectiveNode.Source.Empty     => ref(node.label)
      }
    }

    private lazy val referenceMap: Map[String, ReferenceNode] = {
      val tempRoot = new RootNode
      tempRoot.setReferences(page.markdown.getReferences)
      var result = Map.empty[String, ReferenceNode]
      new ToHtmlSerializer(null) {
        toHtml(tempRoot)
        result = references.asScala.toMap
      }
      result
    }
  }


  case class JupyterDirective(page: Page, variables: Map[String, String])
    extends LeafBlockDirective("jupyter", "jupyter:") with CopiedSourceDirective {

    val baseUrl = PropertyUrl("jupyter.base_url", variables.get)

    val jupyterTargetDir = variables.get("target").map(new File(_))
      .getOrElse(throw new IllegalArgumentException("missing target directory specification"))

    if(!jupyterTargetDir.exists()) jupyterTargetDir.mkdirs()

    def render(node: DirectiveNode, visitor: Visitor, printer: Printer): Unit = {
      val filePath = resolvedSource(node, page)
      val src = if(filePath.startsWith(".") || filePath.startsWith("/")) filePath
      else (baseUrl.resolve() / filePath).base.getPath

      val (basename, _) = IO.split(new File(src).getName)

      val dest = basename + ".md"

      Process(Seq(
        "jupyter",
        "nbconvert",
        "--log-level", "INFO",
        "--to", "markdown",
        src,
        "--output-dir", ".",
        "--output",
        dest
        //(jupyterTargetDir / dest).toString
      ),
        jupyterTargetDir
      ).!

      val markdownText = IO.read(jupyterTargetDir / dest)

      val reader = new Reader()
      val markdownTree = reader.read(markdownText.toArray)

      // Get all the images that were referenced and copy them to the destination
      val imageGatherer = new ImageGatherer
      markdownTree.accept(imageGatherer)
      imageGatherer.getImages
        .map(_.url)
        .map(file(_).getParentFile)
        .distinct
        .foreach(p ⇒ IO.copyDirectory(p, jupyterTargetDir))

      markdownTree.accept(visitor)

//      val lang = Option(node.attributes.value("type")).getOrElse("scala")
//      val group = Option(node.attributes.value("group")).getOrElse("")
//      new VerbatimGroupNode("foo", lang, group).accept(visitor)
    }
  }

  object JupyterDirective {
    def apply(targetDir: File): Writer.Context ⇒ JupyterDirective = (ctx: Writer.Context) ⇒
      JupyterDirective(ctx.location.tree.label,
        ctx.properties + ("target" -> targetDir.getAbsolutePath))
  }

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    paradoxDirectives += JupyterDirective(target.map(_ / "paradox" / "nb").value)
  )
}
