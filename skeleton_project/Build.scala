import sbt._
import Keys._
import play.Project._
import com.typesafe.config._

import steam.build.Nexus
import steam.build.Nexus.latest
import steam.build.Nexus.stable
import steam.build.Module
import steam.build.Dist

object ApplicationBuild extends Build {

  val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

  val appName    = "SkeletonService"
  val appVersion = Module.version

  val appDependencies = Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.1.4",
    "com.typesafe.akka" %% "akka-camel" % "2.1.4",
    "com.typesafe.akka" %% "akka-testkit" % "2.1.4",
    "com.couchbase.client" % "java-client" % "2.1.4",
    "com.typesafe.play.plugins" %% "play-statsd" % "2.1.0",
    "com.github.nscala-time" %% "nscala-time" % "0.6.0",
    "org.scalatest" %% "scalatest" % "1.9.2" % "test" withSources() withJavadoc(),
    "org.mockito" % "mockito-all" % "1.9.5" % "test" withSources() withJavadoc()
  ) ++ Module.dependencies

  val steamdist = Dist.steamdist

  val main = play.Project(appName, appVersion, appDependencies).settings(
       steamdist <<= Dist.steamdistTask,

       scalacOptions += "-feature",

       resolvers ++= Seq("Couchbase Maven Repository" at "http://files.couchbase.com/maven2",
         "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
         "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
         "releases"  at "http://oss.sonatype.org/content/repositories/releases",
         "spray repo" at "http://repo.spray.io") ++ Nexus.repoUrls,

       unmanagedSourceDirectories in Compile <++= baseDirectory { base => Module.localLibDirs map { localLibDir => base / ".." / localLibDir / "src" / "main" / "scala"}},

       // Play/SBT doesn't pickup the config.file option when running in test
       // mode. See https://github.com/playframework/playframework/issues/1017 
       // for a non-explanation.
       javaOptions in Test += "-Dconfig.file=" + Option(System.getProperty("config.file")).getOrElse("conf/application.conf")
  )
}
