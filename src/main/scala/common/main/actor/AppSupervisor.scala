package common.main.actor

import akka.actor.{Actor, ActorRef, PoisonPill, Props}

class AppSupervisor extends Actor{
  override def receive: Receive = {
    case "actor1" =>
      println("app supervisor")
      val actor1Supervisor = context.actorOf(Props[Actor1Supervisor], "actor1supervisor")
      actor1Supervisor ! "Start"
    case "actor2" =>

    case "actor3" =>

    case ref:ActorRef =>
      println("success")
      ref ! PoisonPill
      ref ! "Start"
  }
}
