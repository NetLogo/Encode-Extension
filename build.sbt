import org.nlogo.build.{ NetLogoExtension, ExtensionDocumentationPlugin }

enablePlugins(NetLogoExtension, ExtensionDocumentationPlugin)

name       := "encode"
version    := "1.1.0"
isSnapshot := true

scalaVersion := "3.7.0"

Compile / scalaSource := baseDirectory.value / "src" / "main"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings", "-encoding", "us-ascii", "-Wunused:linted")

netLogoVersion      := "7.0.1"
netLogoClassManager := "org.nlogo.extensions.encode.EncodeExtension"
