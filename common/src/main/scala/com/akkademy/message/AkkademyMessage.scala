package com.akkademy.message

/**
  *
  * @author shawn feng 2018/10/27 11:37
  * @since
  **/
object AkkademyMessage {

  trait Request

  final case class SetRequest(key: String, value: Any) extends Request

  final case class GetRequest(key: String) extends Request

  final case class KeyNotFound(key: String) extends Exception

  final case class SetIfNotExists(key: String, value: Any)

  final case class Delete(key: String)

}
