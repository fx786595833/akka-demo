package com.akkademy

import akka.actor.ActorSystem
import akka.util.Timeout
import com.akkademy.actor.AkkademyDb.GetRequest
import akka.pattern.ask
import com.akkademy.message.AkkademyMessage.{GetRequest, SetRequest}

import scala.concurrent.duration._

/**
  *
  * @author shawn feng 2018/10/27 01:28
  * @since
  **/
class SClient(address: String) {
  private implicit val timeout = Timeout(2 seconds)
  private implicit val system = ActorSystem("LocalSystem")

  private val remoteDb = system.actorSelection(
    s"akka.tcp://akkademy@$address/user/akkademy-db")

  def set(key: String, value: Object) = {
    remoteDb ? SetRequest(key, value)
  }

  def get(key: String) = {
    remoteDb ? GetRequest(key)
  }
}
