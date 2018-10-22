package com.akkademy.temp

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.akkademy.messages.temp.{SendActor, SendRequest}
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._

/**
  *
  * @author shawn feng 2018/10/22 23:25
  * @since
  **/
class SendActorTest extends FunSpecLike with Matchers {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)

  describe("sendActor") {
    describe("given sendRequest") {
      it("should replace string value") {
        val actorRef = TestActorRef(new SendActor)
        actorRef ! SendRequest("test")
        val sendActor = actorRef.underlyingActor
        sendActor.s should equal("test")

        actorRef ! SendRequest("yeah")
        sendActor.s should equal("yeah")
      }
    }
  }
}
