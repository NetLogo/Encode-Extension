import org.nlogo.build.{ NetLogoExtension, ExtensionDocumentationPlugin }

enablePlugins(NetLogoExtension, ExtensionDocumentationPlugin)

name       := "encode"
version    := "1.0.1"
isSnapshot := true

scalaVersion := "3.7.0"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings", "-encoding", "us-ascii")

netLogoVersion      := "7.0.0-beta1-c8d671e"
netLogoClassManager := "org.nlogo.extension.encode.EncodeExtension"
