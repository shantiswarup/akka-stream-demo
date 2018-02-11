package common.main.models

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class Joke(id:Int,joke:String)

object JokeJsonProtocol extends DefaultJsonProtocol {
  implicit val JokeJsonFormat: RootJsonFormat[Joke] = jsonFormat2(Joke)
}