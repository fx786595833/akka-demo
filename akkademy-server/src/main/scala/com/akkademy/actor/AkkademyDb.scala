package com.akkademy.actor

import akka.actor.{Actor, ActorLogging, Props, Status}
import com.akkademy.actor.AkkademyDb.{GetRequest, KeyNotFound, SetRequest}

import scala.collection.mutable

/**
  *
  * @author shawn feng 2018/10/26 23:20
  * @since
  **/

object AkkademyDb {

  def props = Props[AkkademyDb]

  final case class SetRequest(key: String, value: Any)

  final case class GetRequest(key: String)

  final case class KeyNotFound(key: String) extends Exception

}

class AkkademyDb extends Actor with ActorLogging {
  val map = new mutable.HashMap[String, Any]()

  override def receive: Receive = {
    case SetRequest(key, value) =>
      log.info(s"received SetRequest - key: $key value: $value")
      map += (key -> value)
      sender() ! Status.Success
    case GetRequest(key) =>
      log.info(s"received GetRequest - key: $key")
      map(key) match {
        case Some(v) => sender() ! v
        case None => sender() ! Status.Failure(KeyNotFound(key))
      }
    case unknown =>
      log.info(s"received unknown message: $unknown")
      Status.Failure(new ClassNotFoundException)
  }
}
