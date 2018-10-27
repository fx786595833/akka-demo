package com.akkademy.askdemo

import akka.actor.{Actor, ActorLogging, Props}
import com.akkademy.askdemo.ArticleParseActor.{ParseHtmlArticle, ParseResponse}

/**
  *
  * @author shawn feng 2018/10/27 13:03
  * @since
  **/
object ArticleParseActor {
  def props = Props[ArticleParseActor]

  final case class ParseHtmlArticle(body: String)

  final case class ParseResponse(body: String)

}

class ArticleParseActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case ParseHtmlArticle(body) => {
      log.info(s"parse request $body")
      sender() ! ParseResponse(body.substring(1, body.length - 1))
    }
  }
}
