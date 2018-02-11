package common.main.graph

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl.{Balance, Broadcast, Flow, GraphDSL, Merge, Sink, Source}
import akka.{Done, NotUsed}

import scala.concurrent.Future
import scala.concurrent.duration._

object Graph1 {
  def process: Future[Done] = {

    implicit val system: ActorSystem = ActorSystem("test-stream")
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val src: Source[Int, NotUsed] = Source(1 to 100)

    val sink = Sink.foreach(println)

    src.via(balancer).runWith(sink)

  }

  def balancer: Flow[Int, Int, NotUsed] = {
    Flow.fromGraph(
      GraphDSL.create() {
        implicit b =>

          import GraphDSL.Implicits._

          val balance = b.add(Balance[Int](3))

          val mergeBal = b.add(Merge[Int](3))

          for ( i <- 0 until 3)
            balance.out(i) ~> mapping ~> mergeBal.in(i)

          FlowShape(balance.in,mergeBal.out)

      }
    )
  }

  def mapping: Flow[Int, Int, NotUsed] = {
    Flow.fromGraph(
      GraphDSL.create(){
        implicit builder =>

          import GraphDSL.Implicits._

          val flow1 = builder.add(Flow[Int].map(_*20).throttle(1,3.second,0,ThrottleMode.Shaping))

          val flow2 = builder.add(Flow[Int].map(_+2))

          val brdcst = builder.add(Broadcast[Int](2))

          val merge = builder.add(Merge[Int](2))

          brdcst ~> flow1 ~> merge
          brdcst ~> flow2 ~> merge

          FlowShape(brdcst.in, merge.out)
      }
    )


  }
}
