package com.akkademy.telldemo

import akka.actor.ActorSystem
import com.akkademy.telldemo.ArticleServiceActor.ParseArticle
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  *
  * @author shawn feng 2018/10/27 19:50
  * @since
  **/
object Entry {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("ask-system")
    implicit val timeout = Timeout(5 seconds)

    val articleServiceActor = system.actorOf(ArticleServiceActor.props)

    val result = articleServiceActor ? ParseArticle("www.baidu.com") 

    Await.result(result, 5 seconds)

    val result2 = articleServiceActor ? ParseArticle("www.baidu.com")

    Await.result(result2, 5 seconds)

    println(result.value.getOrElse("???"))
  }
}
