import sbt.Keys.{libraryDependencies, mappings}

def mkProject(name: String): Project = {
  Project(name, file(name)).settings(
    scalaVersion := "2.12.7",
    organization := "edu.nju.xinfeng",
    version := "0.0.1-SNAPSHOT",
    resolvers += Resolver.defaultLocal,
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
      "org.scalatest" %% "scalatest" % "3.0.5" % Test
    )
  )
}

name := "akkademy-db-scala"

val akkaVersion = "2.5.17"

lazy val root = Project("akkademy-db-scala", file(".")) aggregate(server, client, common)

lazy val common = mkProject("common").settings(
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-remote" % akkaVersion
  )
)

lazy val server = mkProject("akkademy-server").settings(
  mappings in(Compile, packageBin) ~= {
    _.filterNot { case (_, name) => Seq("application.conf").contains(name)
    }
  }
) dependsOn common

lazy val client = mkProject("akkademy-client") dependsOn common

lazy val akkademaid = mkProject("akkademaid") dependsOn server

lazy val articleParser = mkProject("article-parser").settings(
  libraryDependencies ++= Seq(
    "com.syncthemall" % "boilerpipe" % "1.2.2"
  )
) dependsOn common
