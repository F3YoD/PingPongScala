package model

import play.api.libs.json.Json

case class Page(current: Int, max:Int) {
}

object Page{
  implicit val pageJson = Json.format[Page]
}
