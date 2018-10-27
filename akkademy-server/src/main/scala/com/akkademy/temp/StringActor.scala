package com.akkademy.temp

import akka.actor.{Actor, ActorLogging, Props}

/**
  *
  * @author shawn feng 2018/10/26 23:54
  * @since
  **/
object StringActor {
  def props = Props[StringActor]
}

class StringActor extends Actor with ActorLogging {

  var string = ""

  override def receive: Receive = {
    case s: String => string = s
    case unknown => log.info(s"unknown $unknown")
  }
}
