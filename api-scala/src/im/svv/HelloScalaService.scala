package im.svv

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

class HelloScalaServiceActor extends Actor with HelloScalaService {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}


trait HelloScalaService extends HttpService {

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    }
}
