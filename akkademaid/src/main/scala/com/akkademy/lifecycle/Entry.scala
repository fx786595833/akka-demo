package com.akkademy.lifecycle

import akka.actor.ActorSystem
import com.akkademy.lifecycle.LifecycleActor.{Buffer, IsActive, Offline, Online}

/**
  *
  * @author shawn feng 2018/10/27 21:35
  * @since
  **/
object Entry {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem()

    val lifecycleActor = system.actorOf(LifecycleActor.props)

    lifecycleActor ! Buffer
    lifecycleActor ! Offline
    lifecycleActor ! IsActive
    lifecycleActor ! Online

    lifecycleActor ! Online
    lifecycleActor ! IsActive
    lifecycleActor ! Offline
  }
}
