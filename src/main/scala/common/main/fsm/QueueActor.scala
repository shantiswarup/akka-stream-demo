package common.main.fsm

import akka.actor.Actor
import common.main.fsm.QueueActor.{Dequeue, Enqueue}

object QueueActor {
  final case class Enqueue(value:Any)
  final case object Dequeue
}

class QueueActor[T] extends Actor {
  val queue: List[Any] = List[T]()

  def receive: Receive = {
    case Enqueue(value) =>
      queue.::(value.asInstanceOf[T])
    case Dequeue =>

  }
}
