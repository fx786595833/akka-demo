package com.akkademy.askdemo

import akka.actor.{Actor, ActorLogging, Props}
import com.akkademy.askdemo.HttpClientActor.{GetArticleFromUrl, ResponseArticleFromUrl}

/**
  *
  * @author shawn feng 2018/10/27 13:03
  * @since
  **/

object HttpClientActor {
  def props(url: String) = Props(new HttpClientActor(url))

  case object GetArticleFromUrl

  case class ResponseArticleFromUrl(article: String)

}

class HttpClientActor(url: String) extends Actor with ActorLogging {
  override def receive: Receive = {
    case GetArticleFromUrl =>
      log.info(s"get article from $url")
      sender() ! ResponseArticleFromUrl(s"<$url>")
  }
}
