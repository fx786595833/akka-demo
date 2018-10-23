package com.akkademy.actor

import akka.actor.{Actor, Status}
import akka.event.Logging
import com.akkademy.messages.{GetRequest, KeyNotFoundException, SetRequest}

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
      sender() ! Status.Success
    }
    case GetRequest(key) => {
      log.info(s"received GetRequest - key: $key")
      if (map contains key) sender() ! map.get(key)
      else Status.Failure(new KeyNotFoundException(s"$key not found!"))
    }
    case unknown => {
      log.info(s"received unknown message: $unknown")
      Status.Failure(new ClassNotFoundException)
    }
  }
}
