package com.akkademy

import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Await

import scala.concurrent.duration._

/**
  *
  * @author shawn feng 2018/10/27 01:31
  * @since
  **/
class SClientIntegrationSpec extends FlatSpec with Matchers {
  val client = new SClient("127.0.0.1:2555")

  "akkademyDbClient" should "set a value" in {
    client.set("123", new Integer(123))
    val futureResult = client.get("123")
    val result = Await.result(futureResult, 10 seconds)
    result should equal(123)
  }
}
