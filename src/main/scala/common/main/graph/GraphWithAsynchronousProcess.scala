package common.main.graph

import akka.Done
import common.main.actor.ActorEssential

import scala.concurrent.Future

object GraphWithAsynchronousProcess extends ActorEssential {
  lazy val processUsingMapAsync: Future[Done] =
    Sources.normalSource.via(Flows.mapAsyncFlow).runWith(Sinks.sink)

  lazy val processUsingMapAsyncUnordered: Future[Done] =
    Sources.normalSource.via(Flows.mapAsyncUnorderedFlow).runWith(Sinks.ignore)

  lazy val processUsingThrottleFlow: Future[Done] =
    Sources.normalSource.via(Flows.throttleFlow).runWith(Sinks.sink)

  lazy val processUsingSlowSource: Future[Done] =
    Sources.slowSource.via(Flows.normalFlow).runWith(Sinks.sink)

  lazy val processUsingAsync: Future[Done] =
    Sources.normalSource.via(Flows.asyncFlow).runWith(Sinks.sink)

  lazy val processUsingBalance: Future[Done] =
    Sources.normalSource.via(Flows.flowWithBalance(2,Flows.flowWithDelay)).runWith(Sinks.sink)

  lazy val processUsingBalanceAsync =
    Sources.normalSource.via(Flows.flowWithBalanceAsync(2,Flows.flowWithDelay)).runWith(Sinks.sink)


}
