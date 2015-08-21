import scala.util.Properties

// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.1.5")

resolvers += "Wilkins releases" at Properties.envOrElse("NEXUS_URL", "http://wilkins.corp.gmi.lcl:8888/nexus/content/repositories") + "/releases"

resolvers += "Wilkins snapshots" at Properties.envOrElse("NEXUS_URL", "http://wilkins.corp.gmi.lcl:8888/nexus/content/repositories") + "/snapshots"

addSbtPlugin("com.gmi_mr" % "steam-dist" % "0.4")
