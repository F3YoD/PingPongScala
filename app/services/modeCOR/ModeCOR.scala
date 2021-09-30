package services.modeCOR

import model.{Field, Player}
import services.PingPongService

import scala.collection.mutable.ListBuffer

abstract class ModeCOR(nextCOR: ModeCOR= null,field:Field) {
  def canDo(): Boolean
  def Do(): ListBuffer[Player] = {
    if(canDo()){
      action()
    }
    else{
      if(nextCOR != null){
        nextCOR.Do()
      }
      else{
        PingPongService.players()
      }
    }
  }
  def action(): ListBuffer[Player]
}

/**
 * CODE REVIEW
 * -----------
 * 
 * - you should use val instead of var 
 * 
 */
object ModeCOR {
  def nbVictoriesModesCOR(field:Field): ModeCOR = {
    var COR: ModeCOR = new EqModeCOR(field = field)
    COR = new GtModeCOR(COR,field)
    new LtModeCOR(COR,field)
  }
}
