package common.main.actor

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActor, TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class DummyActorSpec extends TestKit(ActorSystem("MySepc")) with ImplicitSender
  with WordSpecLike with BeforeAndAfterAll with Matchers {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

//  val dummyActor: ActorRef = system.actorOf(Props[DummyActor],"DummyActor")

  val dummyActorProbe: TestProbe =new  TestProbe(system) {
    override def reply(msg: Any): Unit = super.reply(msg)
  }

  val probe = TestProbe()
  probe.setAutoPilot(new TestActor.AutoPilot {
    def run(sender: ActorRef, msg: Any): TestActor.AutoPilot =
      msg match {
        case "stop" ⇒ TestActor.NoAutoPilot
        case x      ⇒ testActor.tell(x, sender); TestActor.KeepRunning
      }
  })

  "Dummy actor " must {
    " send message back " in {
      probe.ref ! "Test1"
      probe.expectMsg("Test1")
    }
  }

}
