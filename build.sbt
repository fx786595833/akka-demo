name := "akkademy-db-scala"
organization := "edu.nju.xinfeng"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.7"

resolvers += Resolver.defaultLocal

val akkaVersion = "2.5.17"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

mappings in(Compile, packageBin) ~= {
  _.filterNot { case (_, name) => Seq("application.conf").contains(name)
  }
}