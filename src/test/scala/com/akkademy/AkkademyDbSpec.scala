package com.akkademy

import akka.actor.{ActorSystem, Props}
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.akkademy.actor.AkkademyDb
import com.akkademy.messages.SetRequest
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._

/**
  *
  * @author shawn feng 2018/10/22 23:08
  * @since
  **/
class AkkademyDbSpec extends FunSpecLike with Matchers {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)

  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key", "value")
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }
    }
  }

  describe("t") {
    val actor = system.actorOf(Props(classOf[AkkademyDb]))
    actor
  }
}
