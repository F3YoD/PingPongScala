package model

import play.api.libs.json.Json

case class Field(name:String, value:Int, mode:String){
}

object Field{
  implicit val fieldJson = Json.format[Field]
}
