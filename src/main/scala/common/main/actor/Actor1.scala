package common.main.actor

import akka.actor.{Actor, ActorRef, Props}

class Actor1 extends Actor{
  override def receive: Receive = {
    case "Start" =>
      println("actor1")
      println(self)
      Processor.process(self)
    case "Done" =>
      println("actor1 done")
      context.parent ! "Done"
  }
}

object Processor {
  def process(origin:ActorRef) ={
    println("process")
    println(origin)
    origin ! "Done"
  }
}