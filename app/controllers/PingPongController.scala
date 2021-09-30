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
    

    /**
     * CODE REVIEW
     * -----------
     * 
     * - never name a variable "data", it's unclear what's inside. Here you can use "playerList"
     * - always specify types when declaring variables => val playerList: Seq[Player]
     * - in scala, method names start with a lowercase letter
     * - never use get to access the value of an option, it will crash if the option is empty, use getOrElse instead
     * - there is no need for mutable variable here (var)
     * - the whole thing can probably be refactored using a for comprehension, much clearer to read and cleaner overall
     *  
     */
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

    /**
     * CODE REVIEW
     * -----------
     * 
     * - watch you code formatting. The last curly bracket should go to the next line for an easier reading experience. 
     * - i don't think you need to parse json using as[Player], there are tools within the framework to help you do that
     * - not entirely sure, but request.body.asJson.get might crash. Let's look for a safer way to write this
     *  
     */
    def addPlayer = Action {request =>
        val json = request.body.asJson.get
        val player = json.as[Player]
        println(player)
        Ok(Json.toJson(PingPongService.addPlayer(player)))}

    /**
     * CODE REVIEW
     * -----------
     * 
     * - there is a way to tell the framwork to automatically parse the output of a controller in JSON and send the according return code (OK, NotFound, ...), let's find out how.
     */
    def playerById(id:Int) = Action {
        val player : Option[Player] = PingPongService.playerById(id)
        if(player.nonEmpty){
            Ok(Json.toJson(player.get))
        }
        else{
            NotFound
        }
    }

    /**
     * CODE REVIEW
     * -----------
     * 
     * - a good practice when you delete something is to return the ID of the deleted object. That way, the frontend can update itself easily. 
     * 
     */
    def removePlayer(id:Int) = Action {
        if(PingPongService.removePlayer(id)) {
            Ok
        }
        else{
            NotFound
        }
    }
}
