import sbt.Keys.mappings

def mkProject(name: String): Project = {
  Project(name, file(name)).settings(
    scalaVersion := "2.12.7",
    organization := "edu.nju.xinfeng",
    version := "0.0.1-SNAPSHOT",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    resolvers += Resolver.defaultLocal
  )
}

name := "akkademy-db-scala"

val akkaVersion = "2.5.17"

lazy val server = mkProject("akkademy-server").settings(
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-remote" % akkaVersion
  ),
  mappings in(Compile, packageBin) ~= {
    _.filterNot { case (_, name) => Seq("application.conf").contains(name)
    }
  }
)

lazy val client = mkProject("akkademy-client") dependsOn server
