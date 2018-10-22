package com.akkademy.messages.temp

import akka.actor.Actor
import akka.event.Logging

/**
  *
  * @author shawn feng 2018/10/22 23:20
  * @since
  **/
class SendActor extends Actor {

  var s: String = ""

  val log = Logging(context.system, this)

  override def receive: Receive = {
    case SendRequest(s) =>
      log.info(s"receive $s")
      this.s = s
    case unknown => log.info(s"unknown: $unknown")
  }
}
