package com.akkademy.actor

import akka.actor.{Actor, ActorLogging, Props}
import com.akkademy.actor.AkkademyDb.SendRequest

import scala.collection.mutable

/**
  *
  * @author shawn feng 2018/10/26 23:20
  * @since
  **/

object AkkademyDb {

  def props = Props[AkkademyDb]

  final case class SendRequest(key: String, value: Any)

}

class AkkademyDb extends Actor with ActorLogging {
  val map = new mutable.HashMap[String, Any]()

  override def receive: Receive = {
    case SendRequest(key, value) =>
      log.info(s"received SetRequest - key: $key value: $value")
      if (!map.contains("key")) map += (key -> value)
    case unknown =>
      log.info(s"received unknown message: $unknown")
  }
}
