name := "akkademy-db-java"
organization := "edu.nju.xinfeng"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.14",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.14" % Test,
  "com.typesafe.akka" %% "akka-remote" % "2.5.14",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)
