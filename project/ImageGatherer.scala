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
import org.pegdown.ast._
import scala.collection.JavaConverters._

/**
 * Nasty old-school Java visitor pattern boilerplate for gathering
 * image nodes in Pegdown AST.
 *
 * @since 7/3/18
 */
private class ImageGatherer extends Visitor {
  private var images = Seq.empty[ExpImageNode]
  def getImages: Seq[ExpImageNode] = images

  override def visit(node: ExpImageNode): Unit = {
    images = images :+ node
  }

  override def visit(node: SuperNode): Unit =
    node.getChildren.asScala.foreach(_.accept(this))

  protected def doVisit(node: Node): Unit = node match {
    case sn: SuperNode ⇒ visit(sn)
    case _ ⇒ ()
  }

  override def visit(node: AbbreviationNode): Unit = doVisit(node)
  override def visit(node: AnchorLinkNode): Unit = doVisit(node)
  override def visit(node: AutoLinkNode): Unit = doVisit(node)
  override def visit(node: BlockQuoteNode): Unit = doVisit(node)
  override def visit(node: BulletListNode): Unit = doVisit(node)
  override def visit(node: CodeNode): Unit = doVisit(node)
  override def visit(node: DefinitionListNode): Unit = doVisit(node)
  override def visit(node: DefinitionNode): Unit = doVisit(node)
  override def visit(node: DefinitionTermNode): Unit = doVisit(node)
  override def visit(node: ExpLinkNode): Unit = doVisit(node)
  override def visit(node: HeaderNode): Unit = doVisit(node)
  override def visit(node: HtmlBlockNode): Unit = doVisit(node)
  override def visit(node: InlineHtmlNode): Unit = doVisit(node)
  override def visit(node: ListItemNode): Unit = doVisit(node)
  override def visit(node: MailLinkNode): Unit = doVisit(node)
  override def visit(node: OrderedListNode): Unit = doVisit(node)
  override def visit(node: ParaNode): Unit = doVisit(node)
  override def visit(node: QuotedNode): Unit = doVisit(node)
  override def visit(node: ReferenceNode): Unit = doVisit(node)
  override def visit(node: RefLinkNode): Unit = doVisit(node)
  override def visit(node: RootNode): Unit = doVisit(node)
  override def visit(node: SimpleNode): Unit = doVisit(node)
  override def visit(node: SpecialTextNode): Unit = doVisit(node)
  override def visit(node: StrikeNode): Unit = doVisit(node)
  override def visit(node: StrongEmphSuperNode): Unit = doVisit(node)
  override def visit(node: TableBodyNode): Unit = doVisit(node)
  override def visit(node: TableCaptionNode): Unit = doVisit(node)
  override def visit(node: TableCellNode): Unit = doVisit(node)
  override def visit(node: TableColumnNode): Unit = doVisit(node)
  override def visit(node: TableHeaderNode): Unit = doVisit(node)
  override def visit(node: TableNode): Unit = doVisit(node)
  override def visit(node: TableRowNode): Unit = doVisit(node)
  override def visit(node: VerbatimNode): Unit = doVisit(node)
  override def visit(node: WikiLinkNode): Unit = doVisit(node)
  override def visit(node: TextNode): Unit = doVisit(node)
  override def visit(node: Node): Unit = doVisit(node)
  override def visit(node: RefImageNode): Unit = doVisit(node)
}
