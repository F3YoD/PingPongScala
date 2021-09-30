package services.fieldCOR

import model.{Field, Player}
import services.PingPongService
import services.modeCOR.ModeCOR

import scala.collection.mutable.ListBuffer

abstract class FieldCOR(nextCOR: FieldCOR = null,field: Field,modeCOR: ModeCOR) {

  def canDo(): Boolean;

  def action(): ListBuffer[Player]

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

object FieldCOR {
  def FieldCOR(field:Field): FieldCOR = {
    var fieldCOR = new fieldNbVictoriesCOR(field = field,modeCOR= ModeCOR.nbVictoriesModesCOR(field))
    fieldCOR
  }
}
