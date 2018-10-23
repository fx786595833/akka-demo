package com.akkademy

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.akkademy.actor.PongActor
import org.scalatest.{FunSpec, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  *
  * @author shawn feng 2018/10/23 10:30
  * @since
  **/
class ScalaAskExamplesTest extends FunSpec with Matchers {

  val system = ActorSystem()
  implicit val timeout = Timeout(5 second)

  val pongActorRef = system.actorOf(Props(classOf[PongActor]))

  describe("Pong actor") {
    it("should respond with Pong") {
      val future = pongActorRef ? "PING"
      val result = Await.result(future.mapTo[String], 1 second)
      assert(result == "PONG")
    }
    it("should fail on unknown message") {
      val future = pongActorRef ? "unknown"
      intercept[Exception] {
        Await.result(future.mapTo[String], 1 second)
      }
    }
  }
}
