package services

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import model.Player

import java.time.LocalDate
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object PingPongService {

  var list: ListBuffer[Player] = ListBuffer[Player](new Player("Bernard","o",0,LocalDate.now()))
  list.addOne(Player("joe","ro",2,LocalDate.now()));
  def pong() = {"Pong"}

  def players(): ListBuffer[Player] = {
    list
  }

  /**
   * CODE REVIEW
   * -----------
   * 
   * - a good practice when you return an updated list is to return it. 
   * - you can also return a tuple like (updatedList, player) to also have the added item in the return data.
   * 
   */
  def addPlayer(player: Player): Player ={
    list.addOne(player).head
  }

  /**
   * CODE REVIEW
   * -----------
   * 
   * - always specify the return type 
   * 
   */
  def filterFromVictoryEq(victoriesNb: Int) ={
    list.filter(player => player.nbVictories == victoriesNb)
  }

  def filterFromVictoryGt(victoriesNb: Int) ={
    list.filter(player => player.nbVictories >= victoriesNb)
  }

  def filterFromVictoryLt(victoriesNb: Int) ={
    list.filter(player => player.nbVictories <= victoriesNb)
  }

  def playerById(id: Int):Option[Player] = {
    if (list.indices.contains(id)){
      Some(list(id))
    }
    else {
      None
    }
  }

  /**
   * CODE REVIEW
   * -----------
   * 
   * - watch you indentation, use a formatter like scalafmt to do it for you
   * 
   */
  def removePlayer(id:Int): Boolean = {
      if(list.indices.contains(id)){
        list.remove(id)
        true
      }
    else{
        false
      }
  }
}
