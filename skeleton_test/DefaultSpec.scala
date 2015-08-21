package test

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._
import org.specs2.mock.Mockito
import org.mockito.Matchers._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith
import play.Logger

/**
 * Add your spec here.
 *
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class DefaultSpec extends Specification with Mockito {


  "AppController routing" should {

    "info" in {

      running(FakeApplication()) {

        val home = route(FakeRequest(GET, "/")).get

        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "application/json")
        val jsonResponse = Json.parse(contentAsString(home))
        jsonResponse \ "steam" must equalTo(JsString("Welcome"))
      }
    }
    "doc" in {

      running(FakeApplication()) {

        val home = route(FakeRequest(GET, "/docs")).get

        Logger.debug(contentType(home).toString())
        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")
      }
    }
  }
}
