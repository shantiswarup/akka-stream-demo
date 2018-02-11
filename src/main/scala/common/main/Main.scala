package common.main

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

import akka.Done
import common.main.graph.GraphWithAsynchronousProcess

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main {
  def main(args: Array[String]): Unit = {

    val time1 = LocalDateTime.now()
    //    val res: Future[Done] = SimpleGraph.graph

    //    val res: Future[Done] = SimpleGraph.graphWithSlowProcesingStage

    //    val res: Future[Done] = GraphWithAsynchronousProcess.processUsingMapAsync

        val res = GraphWithAsynchronousProcess.processUsingMapAsyncUnordered

    //    val res: Future[Done] = GraphWithAsynchronousProcess.processUsingMapAsyncUnordered

    //    val res = GraphWithAsynchronousProcess.processUsingThrottleFlow
    res.onComplete {
      case _ =>
        val time2 = LocalDateTime.now()
        println("process time:" + time1.until(time2, ChronoUnit.MILLIS))
        GraphWithAsynchronousProcess.actorSystem.terminate()
    }
  }
}
