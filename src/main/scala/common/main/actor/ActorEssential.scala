package common.main.actor

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

trait ActorEssential {
  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()(actorSystem)
}
