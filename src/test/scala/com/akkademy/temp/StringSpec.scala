package com.akkademy.temp

import akka.actor.ActorSystem
import akka.testkit.{TestActorRef, TestKit}
import org.scalatest.{FlatSpecLike, Matchers}

/**
  *
  * @author shawn feng 2018/10/26 23:56
  * @since
  **/
class StringSpec extends TestKit(ActorSystem()) with FlatSpecLike with Matchers {

  "string actor" should "contains string" in {
    val actorRef = TestActorRef[StringActor](StringActor.props)

    actorRef ! "test"

    actorRef.underlyingActor.string should equal("test")
  }

  "string actor" should "contains last string" in {
    val actorRef = TestActorRef[StringActor](StringActor.props)

    actorRef ! "test"
    actorRef ! "xixixix"

    actorRef.underlyingActor.string should equal("xixixix")
  }

}
