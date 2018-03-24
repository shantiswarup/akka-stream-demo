package common.main.fsm

import akka.actor.Actor
import akka.stream.scaladsl.Source
import common.main.actor.ActorEssential
import common.main.actor.TestActor1.Finish
import common.main.fsm.PuarQueue.{Completed, Initialize, Process, Processed}
import common.main.graph.{Flows, Sinks}

import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Success}

object PuarQueue {
  case object Initialize
  final case class Process(puar:Int, index:Int)
  final case class Processed(puar:Int, index:Int)
  case object Completed
}

class PuarQueue(puars:List[Int]) extends Actor with ActorEssential{
  override def receive: Receive = {
    case Initialize =>
      println("initializing puar queue")
      context.become(busy)
      self ! Process(puars.head,0)
    case Completed =>
      context.parent ! Finish
  }

  def busy : Receive = {
    case Process(puar,index) =>
      println(s"processing puar $puar with index $index")
      val process = Source.single(puar).via(Flows.flowWithDelay).runWith(Sinks.sink)
      process.onComplete {
        case Success(_) =>
          println(s"process finish for puar $puar with index $index")
          self ! Processed(puar,index)
        case Failure(_) =>
          println(s"puar $puar with index $index failed while processing")

      }
    case Processed(puar,index) =>
      if(index != puars.length - 1) {
        println(s"Processed index $index enqueuing ${index + 1} puar ${puars(index+1)}")
        self ! Process(puars(index+1),index+1)
      } else {
        println("All puar processed")
        context.become(receive)
        self ! Completed
      }
  }
}
