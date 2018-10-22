package com.akkademy.messages

import akka.actor.Actor
import akka.event.Logging

import scala.collection.mutable

/**
  *
  * @author shawn feng 2018/10/22 22:56
  * @since
  **/
class AkkademyDb extends Actor {
  val map = new mutable.HashMap[String, Any]
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case SetRequest(key, value) => {
      log.info(s"received SetRequest - key: $key value: $value")
      map += (key -> value)
    }
    case unknown => log.info(s"received unknown message: $unknown")
  }
}
