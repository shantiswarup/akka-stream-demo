package common.main.actor

import akka.actor.{Actor, Props}
import common.main.actor.TestActor.{Finish, Start}
import common.main.fsm.PuarQueue
import common.main.fsm.PuarQueue.Initialize

object TestActor {
  case object Finish
  case object Start
}

class TestActor extends Actor {
  val puarQueue = context.actorOf(Props(new PuarQueue(List(1,2,3,4,5))))
  override def receive: Receive = {
    case Start =>
      puarQueue ! Initialize
    case Finish =>
      println("Process completed")
    case _ =>
      println("Bad message")
  }
}
