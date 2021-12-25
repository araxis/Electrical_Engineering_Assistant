package com.vm.eea.ui.calculators.fullPanelCalculator

import com.vm.eea.application.PanelId
import com.vm.eea.application.calculators.applicationProject.panelProject.PanelMotor
import com.vm.eea.application.motor.MotorId
import com.vm.eea.ui.CoincidenceFactorField
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.VoltageField

data class UiState(val panelId: PanelId=PanelId(-1),
                   val currentMotorId:MotorId=MotorId(-1),
                   val lineNullVoltage:VoltageField= VoltageField.empty(),
                   val lineLineVoltage: VoltageField= VoltageField.empty(),
                   val coincidenceFactor: CoincidenceFactorField =CoincidenceFactorField.empty(),
                   val demandFactor:CosPhiField =CosPhiField.empty(),
                   val canCalculate:Boolean=false,
                   val loads:List<PanelMotor> = emptyList()
)