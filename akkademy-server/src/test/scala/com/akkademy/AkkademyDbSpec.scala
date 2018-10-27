package com.akkademy

import akka.actor.{ActorSystem}
import akka.testkit.{TestActorRef, TestKit}
import com.akkademy.actor.AkkademyDb
import com.akkademy.message.AkkademyMessage.SetRequest
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

        actorRef ! SetRequest("key", "value")

        actorRef.underlyingActor.map("key") should equal("value")
      }
    }
  }
}
