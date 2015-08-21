package steam.build

import sbt._
import Keys._

import Nexus.latest
import Nexus.stable

object Module {
  val versionFileLines = {
    val f = scala.io.Source.fromFile("VERSION")
    val lines = f.getLines.toList
    f.close
    lines
  }

  lazy val version = Nexus.latest(versionFileLines.head)

  val name = "steam-skeleton-service"

  val organization = "com.gmi_mr"

  val localLibDirs = List(
      //"CommonLib"
      //,"ExpressionService/modules/steam-expression-common"
      //,"ZendClient/modules/gmiservice-server"
      //,"ZendClient/modules/gmiservice-client"
      //,"ZendClient/modules/gmiservice-client-panelist"
      )

  val steamDependencies = Seq(
      ("steam-common","CommonLib",latest("190.0"))
      // examples of using other libs
      //,("steam-expression-common","ExpressionService/modules/steam-expression-common",latest("181.0"))
      //,("gmiservice-server","ZendClient/modules/gmiservice-server",latest("181.0"))
      //,("gmiservice-client","ZendClient/modules/gmiservice-client",latest("181.0"))
      //,("gmiservice-client-panelist","ZendClient/modules/gmiservice-client-panelist",latest("181.0"))
  )

  val dependencies = steamDependencies.filter { case (lib:String,dir:String,version:String) => !localLibDirs.contains(dir) } map {
      case(modulename,dirname,version) => "com.gmi_mr" %% modulename % version withSources() changing()
  }

}
