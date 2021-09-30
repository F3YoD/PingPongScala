package model

import play.api.libs.json.Json

import java.time.LocalDate

/**
 * CODE REVIEW
 * -----------
 * 
 * - property names should start with a lower case letter => dateOfBirth or rather dob in this case
 *  
 */
case class Player(firstName: String, lastName:String, nbVictories: Int, DoB: LocalDate) {
}

object Player{
  implicit val PlayerListJson = Json.format[Player]
}
