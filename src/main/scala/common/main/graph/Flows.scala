package common.main.graph

import akka.NotUsed
import akka.stream.{FlowShape, ThrottleMode}
import akka.stream.scaladsl.{Balance, Flow, GraphDSL, Merge}
import GraphDSL.Implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

object Flows {

  lazy val normalFlow: Flow[Int, Int, NotUsed] = Flow[Int].map(_+1)

  lazy val asyncFlow: Flow[Int, Int, NotUsed] =
    Flow[Int].map(_+1).throttle(1,1.seconds,0,ThrottleMode.shaping).async

  lazy val flowWithDelay: Flow[Int, Int, NotUsed] =
    Flow[Int].map(_+1).throttle(1,1.seconds,0,ThrottleMode.shaping)

  lazy val mapAsyncFlow: Flow[Int, Int, NotUsed] =
    Flow[Int].mapAsync[Int](10)(futureResponseWithConstantResponseTime)

  lazy val mapAsyncUnorderedFlow: Flow[Int, Int, NotUsed] =
    Flow[Int].mapAsyncUnordered[Int](10)(futureResponseWithConstantResponseTime)

  lazy val mapAsyncFlowDelay: Flow[Int, Int, NotUsed] =
    Flow[Int].mapAsync(4)(futureResponseWithConstantResponseTime).throttle(1,1.seconds,0,ThrottleMode.shaping)

  lazy val throttleFlow: Flow[Int, Int, NotUsed] =
    Flow[Int].throttle(1,1.seconds,2,ThrottleMode.shaping).map(_+1)

  lazy val throughput: Flow[Int, Int, NotUsed] =
    Flow[Int].map(_=>1)

  lazy val flowWithAsync: Flow[Int, Int, NotUsed] =
    Flow.fromGraph(GraphDSL.create(){
      implicit b =>
        val first = b.add(flowWithDelay.async)
        val second = b.add(flowWithDelay)

        first ~> second

        FlowShape(first.in,second.out)
    })


  def flowWithBalance(n:Int,flow:Flow[Int,Int,NotUsed]) = Flow.fromGraph(
    GraphDSL.create() {
      implicit b =>
        val balance = b.add(Balance[Int](n))
        val merge = b.add(Merge[Int](n))
        for (i <- 0 until n ) {
          balance.out(i) ~> flow  ~> merge.in(i)
        }

        FlowShape(balance.in,merge.out)
    }
  )

  def flowWithBalanceAsync(n:Int,flow:Flow[Int,Int,NotUsed]) = Flow.fromGraph(
    GraphDSL.create() {
      implicit b =>
        val balance = b.add(Balance[Int](n))
        val merge = b.add(Merge[Int](n))
        for (i <- 0 until n ) {
          balance.out(i) ~> flow.async ~> merge.in(i)
        }

        FlowShape(balance.in,merge.out)
    }
  )

  def futureResponseWithrandomResponseTime(x:Int): Future[Int] ={
    Thread.sleep(scala.util.Random.nextInt(2000))
    Future(x+1)
  }

  def futureResponseWithConstantResponseTime(x:Int): Future[Int] = {
    Thread.sleep(10)
    Future.apply[Int](x)
  }

}
