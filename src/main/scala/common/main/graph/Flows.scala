package common.main.graph

import akka.NotUsed
import akka.stream.ThrottleMode
import akka.stream.scaladsl.Flow

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

object Flows {

  lazy val normalFlow: Flow[Int, Int, NotUsed] = Flow[Int].map(_+1)

  lazy val flowWithDelay: Flow[Int, Int, NotUsed] =
    Flow[Int].map(_+1).throttle(1,2.seconds,0,ThrottleMode.shaping)

  lazy val asyncFlow: Flow[Int, Int, NotUsed] =
    Flow[Int].mapAsync[Int](10)(futureResponseWithConstantResponseTime)

  lazy val asyncUnorderedFlow: Flow[Int, Int, NotUsed] =
    Flow[Int].mapAsyncUnordered[Int](10)(futureResponseWithConstantResponseTime)

  lazy val asyncFlowDelay: Flow[Int, Int, NotUsed] =
    Flow[Int].mapAsync(4)(futureResponseWithConstantResponseTime).throttle(1,1.seconds,0,ThrottleMode.shaping)

  lazy val throttleFlow: Flow[Int, Int, NotUsed] =
    Flow[Int].throttle(1,1.seconds,2,ThrottleMode.shaping).map(_+1)

  lazy val throughput: Flow[Int, Int, NotUsed] =
    Flow[Int].map(_=>1)

  def futureResponseWithrandomResponseTime(x:Int): Future[Int] ={
    Thread.sleep(scala.util.Random.nextInt(2000))
    Future(x+1)
  }

  def futureResponseWithConstantResponseTime(x:Int): Future[Int] = {
    Thread.sleep(10)
    Future.apply[Int](x)
  }

}
