package controllers

import model.{Field, Page, Player}
import play.api.libs.json.Format.GenericFormat
import play.api.mvc.{AbstractController, ControllerComponents}
import services.{PaginationService, PingPongService}
import play.api.libs.json._
import services.fieldCOR.FieldCOR

import javax.inject.{Inject, Singleton}

@Singleton
class PingPongController @Inject()(cc: ControllerComponents) extends AbstractController(cc){
    def Pong() = Action {Ok(PingPongService.pong())}
    def players = Action {request =>
        var data = PingPongService.players()
        val field = request.getQueryString("field")
        if(field.nonEmpty){
            val fieldObject = Json.parse(field.get).as[Field]
            data= FieldCOR.FieldCOR(fieldObject).Do()
        }
        val page= request.getQueryString("page")
        if(page.nonEmpty){
            val pageObject= Json.parse(page.get).as[Page]
            data = PaginationService.pagination(pageObject,data)
        }
        Ok(Json.toJson(data))
    }
    def addPlayer = Action {request =>
        val json = request.body.asJson.get
        val player = json.as[Player]
        println(player)
        Ok(Json.toJson(PingPongService.addPlayer(player)))}

    def playerById(id:Int) = Action {
        val player : Option[Player] = PingPongService.playerById(id)
        if(player.nonEmpty){
            Ok(Json.toJson(player.get))
        }
        else{
            NotFound
        }
    }

    def removePlayer(id:Int) = Action {
        if(PingPongService.removePlayer(id)) {
            Ok
        }
        else{
            NotFound
        }
    }
}
