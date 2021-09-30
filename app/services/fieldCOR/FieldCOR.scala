package services.fieldCOR

import model.{Field, Player}
import services.PingPongService
import services.modeCOR.ModeCOR

import scala.collection.mutable.ListBuffer

/**
 * CODE REVIEW
 * -----------
 * 
 * - this should probably be a trait instead of an abstract class, as it defines behavioral functionalities.
 * 
 */
abstract class FieldCOR(nextCOR: FieldCOR = null,field: Field,modeCOR: ModeCOR) {

  def canDo(): Boolean;

  def action(): ListBuffer[Player]

  /**
     * CODE REVIEW
     * -----------
     * 
     * - method names should start with a lower case letter
     * - never use null in scala, instead use Options
     * 
     */
  def Do(): ListBuffer[Player] = {
    if(this.canDo()){
      this.action()
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
}

/**
  * CODE REVIEW
  * -----------
  * 
  * - there is no need for var here. in fact there is no need for a variable at all.
  *  
  */
object FieldCOR {
  def FieldCOR(field:Field): FieldCOR = {
    var fieldCOR = new fieldNbVictoriesCOR(field = field,modeCOR= ModeCOR.nbVictoriesModesCOR(field))
    fieldCOR
  }
}
