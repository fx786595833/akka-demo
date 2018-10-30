package com.akkademy.lifecycle

import akka.actor.{Actor, ActorLogging, Props, Stash}
import com.akkademy.lifecycle.LifecycleActor._

/**
  *
  * @author shawn feng 2018/10/27 21:06
  * @since
  **/
object LifecycleActor {
  def props = Props[LifecycleActor]

  case object Online

  case object Offline

  case object IsActive

  case object Buffer

}

class LifecycleActor extends Actor with Stash with ActorLogging {

  override def receive: Receive = {
    case Buffer =>
      stash()
    case Online =>
      log.info("hello")
      context.become(online)
      unstashAll()
    case Offline =>
      log.info("already offline")
    case IsActive =>
      log.info("is not active")
  }

  private def online: Receive = {
    case Offline =>
      log.info("bye bye")
      context.unbecome()
    case Online =>
      log.info("already online")
    case IsActive =>
      log.info("is active")
  }
}
