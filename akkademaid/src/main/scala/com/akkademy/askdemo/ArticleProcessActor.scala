package com.akkademy.askdemo

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.akkademy.askdemo.ArticleCacheActor.{GetRespond, NoCache, SetRequest}
import com.akkademy.askdemo.ArticleParseActor.{ParseHtmlArticle, ParseResponse}
import com.akkademy.askdemo.HttpClientActor.{GetArticleFromUrl, ResponseArticleFromUrl}

/**
  *
  * @author shawn feng 2018/10/27 19:12
  * @since
  **/
object ArticleProcessActor {
  def props(sender: ActorRef, cache: ActorRef, url: String) = Props(new ArticleProcessActor(sender, cache, url))
}

class ArticleProcessActor(sender: ActorRef, cache: ActorRef, url: String) extends Actor with ActorLogging {
  val httpClientActor = context.actorOf(HttpClientActor.props(url))

  override def receive: Receive = {
    case GetRespond(article) =>
      sender ! article
      context.stop(self)
    case NoCache =>
      httpClientActor ! GetArticleFromUrl
    case ResponseArticleFromUrl(article) =>
      val parseActor = context.actorOf(ArticleParseActor.props)
      parseActor ! ParseHtmlArticle(article)
    case ParseResponse(body) =>
      cache ! SetRequest(url, body)
      sender ! body
      context.stop(self)
    case "timeout" =>
      log.warning("time is up!!!")
      context.stop(self)
    case unknown =>
      log.warning(s"unknown director $unknown")
  }
}
