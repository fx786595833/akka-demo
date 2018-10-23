package com.akkademy.actor

import akka.actor.{Actor, Status}

/**
  *
  * @author shawn feng 2018/10/23 00:41
  * @since
  **/
class PongActor extends Actor {
  override def receive: Receive = {
    case "PING" => sender() ! "PONG"
    case _ => sender() ! Status.Failure(new Exception("unknown message"))
  }
}
