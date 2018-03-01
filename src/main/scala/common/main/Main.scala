package common.main

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

import akka.Done
import akka.actor.Props
import common.main.actor.TestActor.Start
import common.main.actor.{ActorEssential, TestActor}
import common.main.graph.GraphWithAsynchronousProcess

import scala.collection.immutable
import scala.collection.parallel.immutable.ParSeq
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main extends ActorEssential{
  def main(args: Array[String]): Unit = {

//    val time1 = LocalDateTime.now()
    //    val res: Future[Done] = SimpleGraph.graph

    //    val res: Future[Done] = SimpleGraph.graphWithSlowProcesingStage

    //    val res: Future[Done] = GraphWithAsynchronousProcess.processUsingMapAsync

//        val res = GraphWithAsynchronousProcess.processUsingMapAsyncUnordered

    //    val res: Future[Done] = GraphWithAsynchronousProcess.processUsingMapAsyncUnordered


//        val res = GraphWithAsynchronousProcess.processUsingThrottleFlow
//
    // process-time: 28011 milliseconds
//    val res = GraphWithAsynchronousProcess.processUsingAsync

    // process-time: 13641 milliseconds
//    val res: Future[Done] = GraphWithAsynchronousProcess.processUsingBalance

    //process-time: 18994 milliseonds (order not maintained)
//    val res = GraphWithAsynchronousProcess.processUsingBalanceAsync


//    res.onComplete {
//      case _ =>
//        val time2 = LocalDateTime.now()
//        println("process time:" + time1.until(time2, ChronoUnit.MILLIS))
//        GraphWithAsynchronousProcess.actorSystem.terminate()
//    }

//    val a: immutable.Seq[Int] = for (i <- 1 to 100000 ) yield {
//      scala.util.Random.nextInt(4000)
//    }
//    val time1 = LocalDateTime.now()
//
//    val res: (immutable.Seq[Int], immutable.Seq[Int]) = a partition( _ % 2 == 0)
//    val time2 = LocalDateTime.now()
//
//    println(res._1)
//    println(res._2)
//    println("process time:" + time1.until(time2, ChronoUnit.MILLIS))

    val testActor = actorSystem.actorOf(Props[TestActor])
    testActor ! Start

  }
}
