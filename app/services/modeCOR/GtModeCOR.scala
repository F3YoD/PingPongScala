package services.modeCOR

import model.{Field, Player}
import services.PingPongService

import scala.collection.mutable.ListBuffer

class GtModeCOR(nextCOR: ModeCOR = null,field: Field) extends ModeCOR(nextCOR,field) {
  override def canDo(): Boolean = {
    field.mode.equals("gt");
  }

  override def action(): ListBuffer[Player] = {
    PingPongService.filterFromVictoryGt(field.value)
  }
}
