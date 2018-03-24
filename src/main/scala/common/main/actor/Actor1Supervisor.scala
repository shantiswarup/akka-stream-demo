package common.main.actor

import akka.actor.{Actor, Props}

class Actor1Supervisor extends Actor {
  val actor1 = context.actorOf(Props[Actor1],"actor1")

  override def receive: Receive = {
    case "Start" =>
      println("actor1 supervisor")
      actor1 ! "Start"
    case "Done" =>
      println("actor 1 supervisor done")
      context.parent ! self

  }
}
