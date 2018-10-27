package com.akkademy

import akka.actor.{ActorSystem, Status}
import akka.pattern.ask
import akka.testkit.{TestKit, TestProbe}
import akka.util.Timeout
import com.akkademy.actor.ReverseStringActor
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  *
  * @author shawn feng 2018/10/27 11:13
  * @since
  **/
class StringSpec extends TestKit(ActorSystem()) with FunSpecLike with Matchers {

  describe("reverse actor") {
    it("return reverse string when sending string") {
      val probe = TestProbe()
      val actor = system.actorOf(ReverseStringActor.props)

      actor.tell("test", probe.ref)

      probe.expectMsg("tset")
    }

    it("should failure when receive non-string") {
      val probe = TestProbe()
      val actor = system.actorOf(ReverseStringActor.props)

      actor.tell(1, probe.ref)

      probe.expectMsgType[Status.Failure]
    }

    it("return reverse strings when sending list of string") {
      implicit val timeout = Timeout(2 seconds)
      import scala.concurrent.ExecutionContext.Implicits.global

      val actor = system.actorOf(ReverseStringActor.props)

      val listFuture = List(actor ? "test", actor ? "spec")

      val futureList = Future.sequence(listFuture)

      Await.result(futureList, 2 seconds)

      val result = futureList.value.get

      result.get should equal(List("tset", "ceps"))
    }
  }
}
