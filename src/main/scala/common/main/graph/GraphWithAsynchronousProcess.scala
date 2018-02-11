package common.main.graph

import akka.Done
import common.main.actor.ActorEssential

import scala.concurrent.Future

object GraphWithAsynchronousProcess extends ActorEssential {
  lazy val processUsingMapAsync: Future[Done] =
    Sources.normalSource.via(Flows.asyncFlow).runWith(Sinks.sink)

  lazy val processUsingMapAsyncUnordered: Future[Done] =
    Sources.normalSource.via(Flows.asyncUnorderedFlow).runWith(Sinks.ignore)

  lazy val processUsingThrottleFlow: Future[Done] =
    Sources.normalSource.via(Flows.throttleFlow).runWith(Sinks.sink)

}
