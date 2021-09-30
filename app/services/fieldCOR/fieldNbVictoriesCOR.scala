package services.fieldCOR

import model.Field
import services.modeCOR.ModeCOR

class fieldNbVictoriesCOR(nextCOR: FieldCOR = null,field:Field, modeCOR: ModeCOR= null) extends FieldCOR(nextCOR, field,modeCOR) {
  override def canDo(): Boolean = {
    field.name.equals("nbVictories")
  }

  override def action() = {
    this.modeCOR.Do()
  }
}
