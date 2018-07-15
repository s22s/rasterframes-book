name := "RasterFrames User Manual"

enablePlugins(ParadoxPlugin, JupyterDocsPlugin)

paradoxTheme := Some(builtinParadoxTheme("generic"))

scalaVersion := "2.11.12"

paradoxProperties ++= Map(
  "github.base_url" -> "https://github.com/locationtech/rasterframes",
  "project.name" -> "RasterFrames",
  "jupyter.base_url" -> ((Compile / sourceDirectory).value / "notebooks").toString
)
paradoxTheme := Some(builtinParadoxTheme("generic"))
paradoxGroups := Map("Language" -> Seq("Scala", "Python"))
paradoxTheme / sourceDirectory := sourceDirectory.value / "main" / "paradox" / "_template"