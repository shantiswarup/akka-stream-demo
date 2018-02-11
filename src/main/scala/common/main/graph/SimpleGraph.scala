package common.main.graph

import akka.Done
import common.main.actor.ActorEssential

import scala.concurrent.Future

object SimpleGraph extends ActorEssential {

  lazy val graph: Future[Done] =
    Sources.normalSource.via(Flows.normalFlow).runWith(Sinks.sink)

  lazy val graphWithSlowProcesingStage: Future[Done] =
    Sources.normalSource.via(Flows.flowWithDelay).runWith(Sinks.sink)

  lazy val graphWithAsyncProcess =
    Sources.normalSource.via(Flows.asyncFlow).runWith(Sinks.sink)
}
