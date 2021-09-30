package model

import play.api.libs.json.Json

import java.time.LocalDate

case class Player(firstName: String, lastName:String, nbVictories: Int, DoB: LocalDate) {
}

object Player{
  implicit val PlayerListJson = Json.format[Player]
}
