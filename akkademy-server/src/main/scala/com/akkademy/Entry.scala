package com.akkademy

import akka.actor.ActorSystem
import com.akkademy.actor.AkkademyDb

/**
  *
  * @author shawn feng 2018/10/27 01:24
  * @since
  **/
object Entry {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("akkademy")
    system.actorOf(AkkademyDb.props, "akkademy-db")
  }
}
