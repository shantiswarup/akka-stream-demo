package common.main.graph

import akka.stream.scaladsl.{Flow, GraphDSL, Source}
import GraphDSL.Implicits._
import akka.NotUsed
import akka.stream.{SourceShape, ThrottleMode}
import scala.concurrent.duration._

object Sources {
  lazy val normalSource: Source[Int, NotUsed] = Source(1 to 1000)

  lazy val slowSource: Source[Int, NotUsed] = Source.fromGraph(GraphDSL.create(){
    implicit b =>

      val numbers: SourceShape[Int] = b.add(Source(1 to 1000))
      val throttler = b.add(Flow[Int].throttle(1,1.seconds,0,ThrottleMode.Shaping))

      numbers ~> throttler

      SourceShape(throttler.out)
  })
}
