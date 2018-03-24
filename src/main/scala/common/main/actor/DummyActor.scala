package common.main.actor

import akka.actor.Actor

class DummyActor extends Actor {
  override def receive: Receive = {
    case "Test1" =>
      println(context.parent)
      println(sender())
      sender() ! "Test1Success"
  }
}
