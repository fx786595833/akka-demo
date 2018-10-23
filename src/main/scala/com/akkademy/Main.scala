package com.akkademy

import akka.actor.{ActorSystem, Props}
import com.akkademy.actor.AkkademyDb

/**
  *
  * @author shawn feng 2018/10/23 11:07
  * @since
  **/
object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("akkademy")
    system.actorOf(Props(classOf[AkkademyDb]),"akkademy-db")
  }
}
