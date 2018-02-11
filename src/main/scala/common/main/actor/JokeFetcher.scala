package common.main.actor

import akka.actor.Actor
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal
import common.main.models.Joke
import common.main.models.JokeJsonProtocol._

import scala.concurrent.Future
import scala.language.postfixOps


object JokeFetcher {

  case object Fetch
  case class Fetched(joke:Joke)

//  val http = Http(ActorEssential.actorSystem)

  val url = "http://api.icndb.com/jokes/random?escape=javascript"
}

class JokeFetcher extends Actor {
  override def receive: Receive = {
    case "" =>
//      val jokeResponse: Future[Option[Joke]] =
//        http.singleRequest(HttpRequest(method = HttpMethods.GET,url)).flatMap{
//          httpResponse =>
//            httpResponse.status match {
//              case StatusCodes.OK =>
//                val futureResult: Future[Joke] = Unmarshal(httpResponse.entity).to[Joke]
//                futureResult.map(x=>Some(x))
//              case _ =>
//                Future.successful(None)
//            }
//        }
//       self ! jokeResponse

    case Some(Joke) =>

  }
}
