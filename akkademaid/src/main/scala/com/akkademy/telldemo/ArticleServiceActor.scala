package com.akkademy.telldemo

import akka.actor.{Actor, Props}
import akka.util.Timeout
import com.akkademy.telldemo.ArticleCacheActor.GetRequest
import com.akkademy.telldemo.ArticleServiceActor.ParseArticle
import com.akkademy.telldemo.HttpClientActor.GetArticleFromUrl

import scala.concurrent.duration._

/**
  *
  * @author shawn feng 2018/10/27 13:02
  * @since
  **/
object ArticleServiceActor {
  def props = Props[ArticleServiceActor]

  final case class ParseArticle(url: String)

}

class ArticleServiceActor extends Actor {

  val cacheClientActor = context.actorOf(ArticleCacheActor.props)
  implicit val timeout = Timeout(3 seconds)

  override def receive: Receive = {
    case ParseArticle(url) =>
      import context.dispatcher

      val extraActor = context.actorOf(ArticleProcessActor.props(sender(), cacheClientActor, url))


      cacheClientActor.tell(GetRequest(url), extraActor)

      context.system.scheduler.scheduleOnce(5 seconds, extraActor, "timeout")
  }
}
