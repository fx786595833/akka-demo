package com.akkademy

import akka.actor.FSM
import com.akkademy.FSMClientActor._
import com.akkademy.message.AkkademyMessage.Request

import scala.concurrent.duration._

/**
  *
  * @author shawn feng 2018/10/30 16:34
  * @since
  **/

object FSMClientActor {

  sealed trait State

  case object Disconnected extends State

  case object Connected extends State

  case object ConnectedAndPending extends State

  case object Flush

  sealed trait Data

  case object NoData extends Data

  case class RequestQueue(queue: Seq[Request]) extends Data

}

class FSMClientActor(address: String) extends FSM[State, Data] {
  private val remoteDb = context.system.actorSelection(address)

  startWith(Disconnected, NoData)

  when(Disconnected) {
    case Event(Connected, NoData) =>
      goto(Connected)
    case Event(Connected, q: RequestQueue) =>
      goto(ConnectedAndPending)
    case Event(x: Request, NoData) =>
      stay using RequestQueue(Seq(x))
    case Event(x: Request, q: RequestQueue) =>
      stay using q.copy(queue = q.queue :+ x)
  }

  when(Connected) {
    case Event(x: Request, NoData) =>
      goto(ConnectedAndPending) using RequestQueue(Seq(x))
    case Event(x: Request, q: RequestQueue) =>
      goto(ConnectedAndPending) using q.copy(queue = q.queue :+ x)
  }

  when(ConnectedAndPending, stateTimeout = 5 seconds) {
    case Event(x: Request, q: RequestQueue) =>
      stay using q.copy(queue = q.queue :+ x)
    case Event(Flush | StateTimeout, q: RequestQueue) =>
      goto(Connected) using NoData
  }

  onTransition {
    case ConnectedAndPending -> Connected => {
      remoteDb ! stateData
    }
  }

  initialize()
}
