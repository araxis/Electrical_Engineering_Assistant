package com.vm.eea.ui.panel.fullPanelUpdater

import com.vm.eea.ui.CoincidenceFactorField
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.StringField

data class UiState(val code:StringField = StringField.empty(),
              val coincidenceFactor: CoincidenceFactorField = CoincidenceFactorField.empty(),
              val demandFactor: CosPhiField = CosPhiField.empty(),
              val canCalculate:Boolean=false) {
}