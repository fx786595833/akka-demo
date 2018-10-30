import akka.actor.SupervisorStrategy._
import akka.actor.{Actor, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}
import akka.routing.RoundRobinPool

import scala.concurrent.Future

/**
  *
  * @author shawn feng 2018/10/30 17:13
  * @since
  **/
object ArticleParser {
  def apply(html: String): String = de.l3s.boilerpipe.extractors
    .ArticleExtractor.INSTANCE.getText(html)

  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global

    val articleList = List("www.baidu.com", "www.sohu.com", "www.ximalaya.com")
    val futures = articleList.map(article =>
      Future {
        ArticleParser(article)
      }
    )

    val results = Future.sequence(futures)
  }
}

final case class ParseArticle(html: String)

class ArticleActor extends Actor {
  override def receive: Receive = {
    case ParseArticle(html) =>
      val article = ArticleParser(html)
      sender() ! article
  }
}

object ArticleActor {
  def props = Props[ArticleActor]

  def main(args: Array[String]): Unit = {
    val system = ActorSystem()
    val workerRouter = system.actorOf(ArticleActor.props
      .withRouter(new RoundRobinPool(8)
        .withSupervisorStrategy(strategy)))
  }

  val strategy: SupervisorStrategy = OneForOneStrategy() {
    case e: IllegalAccessException => Escalate
    case e: NullPointerException => Stop
    case e: ArithmeticException => resume
    case _: Exception => restart
    case _ => Escalate
  }
}