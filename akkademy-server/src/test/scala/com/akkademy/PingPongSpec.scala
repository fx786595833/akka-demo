package com.akkademy

import akka.actor.{ActorSystem, Status}
import akka.testkit.{TestKit, TestProbe}
import com.akkademy.actor.PongActor
import org.scalatest.{FunSpecLike, Matchers}

/**
  *
  * @author shawn feng 2018/10/27 00:24
  * @since
  **/
class PingPongSpec extends TestKit(ActorSystem()) with FunSpecLike with Matchers {

  describe("Pong actor") {
    it("should respond with pong") {
      val probe = TestProbe()

      val pongActor = system.actorOf(PongActor.props)

      pongActor.!("ping")(probe.ref)

      probe.expectMsg("pong")
    }

    it("should fail on unknown message") {
      val probe = TestProbe()

      val pongActor = system.actorOf(PongActor.props)

      pongActor.!("unknown")(probe.ref)

      probe.expectMsgType[Status.Failure]
    }
  }

}
