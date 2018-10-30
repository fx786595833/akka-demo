package com.akkademy

import akka.actor.ActorSystem
import akka.util.Timeout
import com.akkademy.message.AkkademyMessage.{GetRequest, SetRequest}

import scala.concurrent.duration._
import akka.pattern.ask

import scala.util.Success

/**
  *
  * @author shawn feng 2018/10/23 12:51
  * @since
  **/
class SClient(remoteAddress: String) {

  implicit val system = ActorSystem()
  implicit val timeout = Timeout(2 seconds)
  private val remoteDb = system.actorSelection(
    s"akka.tcp://akkademy@$remoteAddress/user/akkademy-db")

  def set(k: String, v: String) = {
    remoteDb ? SetRequest(k, v)
  }

  def get(k: String) = {
    remoteDb ? GetRequest(k)
  }

}

object SClient {
  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global

    val sClient = new SClient("127.0.0.1:2555")

    sClient.set("hi", "hei")
    val result = sClient.get("hi")
    result.onComplete {
      case Success(v) => print(s"i got you $v")
    }
  }
}