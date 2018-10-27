package com.akkademy.telldemo

import akka.actor.{Actor, ActorLogging, Props}
import com.akkademy.telldemo.ArticleCacheActor.{GetRequest, GetRespond, NoCache, SetRequest}

import scala.collection.mutable

/**
  *
  * @author shawn feng 2018/10/27 13:03
  * @since
  **/
object ArticleCacheActor {
  def props = Props[ArticleCacheActor]

  final case class GetRequest(url: String)

  final case class GetRespond(article: String)

  final case class SetRequest(url: String, body: String)

  case object NoCache

}

class ArticleCacheActor extends Actor with ActorLogging {
  val cache = mutable.Map[String, String]()

  override def receive: Receive = {
    case GetRequest(url) =>
      cache.get(url) match {
        case Some(article) =>
          log.info("i found it from cache")
          sender() ! GetRespond(article)
        case None =>
          log.info("there is no such article from cache")
          sender() ! NoCache
      }
    case SetRequest(url, body) =>
      log.info(s"put $url->$body into cache")
      cache += (url -> body)
  }
}
