package com.akkademy.message

/**
  *
  * @author shawn feng 2018/10/27 11:37
  * @since
  **/
object AkkademyMessage {

  final case class SetRequest(key: String, value: Any)

  final case class GetRequest(key: String)

  final case class KeyNotFound(key: String) extends Exception

  final case class SetIfNotExists(key: String, value: Any)

  final case class Delete(key: String)

}
