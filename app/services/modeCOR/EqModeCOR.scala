package services.modeCOR

import model.{Field, Player}
import services.PingPongService

import scala.collection.mutable.ListBuffer

class EqModeCOR(nextCOR: ModeCOR = null,field: Field) extends ModeCOR(nextCOR,field){
  override def canDo(): Boolean = {
    field.mode.equals("eq")
  }

  override def action(): ListBuffer[Player] = {
    PingPongService.filterFromVictoryEq(field.value)
  }
}
