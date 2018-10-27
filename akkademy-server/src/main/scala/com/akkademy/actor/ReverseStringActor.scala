package com.akkademy.actor

import akka.actor.{Actor, ActorLogging, Props, Status}

/**
  *
  * @author shawn feng 2018/10/27 11:09
  * @since
  **/
object ReverseStringActor {
  def props = Props[ReverseStringActor]
}

class ReverseStringActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case s: String => sender() ! s.reverse
    case unknown =>
      log.warning(s"unknown: $unknown")
      sender() ! Status.Failure(new NoSuchElementException)
  }
}
