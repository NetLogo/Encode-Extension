import org.nlogo.build.{ NetLogoExtension, ExtensionDocumentationPlugin }

enablePlugins(NetLogoExtension, ExtensionDocumentationPlugin)

name       := "encode"
version    := "1.1.0"
isSnapshot := true

scalaVersion := "3.7.0"

Compile / scalaSource := baseDirectory.value / "src" / "main"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings", "-encoding", "us-ascii")

netLogoVersion      := "7.0.0-beta1-c8d671e"
netLogoClassManager := "org.nlogo.extensions.encode.EncodeExtension"
