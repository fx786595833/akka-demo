package com.akkademy.actor

import akka.actor.{Actor, Props, Status}

/**
  *
  * @author shawn feng 2018/10/27 00:12
  * @since
  **/
object PingActor {
  def props = Props[PingActor]
}

class PingActor extends Actor {
  override def receive: Receive = {
    case "pong" => sender() ! "ping"
    case _ =>
      sender() ! Status.Failure(new Exception("unknown type"))
  }
}

object PongActor {
  def props = Props[PongActor]
}

class PongActor extends Actor {
  override def receive: Receive = {
    case "ping" => sender() ! "pong"
    case _ =>
      sender() ! Status.Failure(new Exception("unknown type"))
  }
}