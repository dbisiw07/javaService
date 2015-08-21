package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.util.control._
import org.joda.time.DateTime
import steam.common.controllerUtils.SteamConventions._
import steam.common.couchbase._
import play.libs.Akka


object SkeletonController extends Controller {
	def getPage(pageId: String) = Action {
	  Ok("OK")
	}
}