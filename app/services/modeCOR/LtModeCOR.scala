package services.modeCOR

import model.{Field, Player}
import services.PingPongService

import scala.collection.mutable.ListBuffer

class LtModeCOR (nextCOR: ModeCOR = null,field: Field) extends ModeCOR(nextCOR,field){
  override def canDo(): Boolean = {
    field.mode.equals("lt");
  }

  override def action(): ListBuffer[Player] = {
    PingPongService.filterFromVictoryLt(field.value)
  }
}
