package com.akkademy

import akka.actor.{ActorSystem, Props}
import akka.testkit.{TestActorRef, TestKit, TestProbe}
import com.akkademy.actor.AkkademyDb
import com.akkademy.actor.AkkademyDb.SendRequest
import org.scalatest.{FunSpecLike, Matchers}

/**
  *
  * @author shawn feng 2018/10/22 23:08
  * @since
  **/
class AkkademyDbSpec extends TestKit(ActorSystem()) with FunSpecLike with Matchers {

  describe("akkademyDb") {
    describe("given SendRequest") {
      it("should place key/value into map") {
        val actorRef = TestActorRef[AkkademyDb](AkkademyDb.props)

        actorRef ! SendRequest("key", "value")

        actorRef.underlyingActor.map("key") should equal("value")
      }
    }
  }
}
