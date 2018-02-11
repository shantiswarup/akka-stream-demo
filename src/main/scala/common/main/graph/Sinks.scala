package common.main.graph

import akka.{Done, NotUsed}
import akka.stream.{SinkShape, ThrottleMode}
import akka.stream.scaladsl.{Flow, GraphDSL, Sink}

import scala.concurrent.Future
import scala.concurrent.duration._
import GraphDSL.Implicits._

object Sinks {

  lazy val sink: Sink[Any, Future[Done]] = Sink.foreach(println)

  lazy val ignore: Sink[Any, Future[Done]] = Sink.ignore
  
}
