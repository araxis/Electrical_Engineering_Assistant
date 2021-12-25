package com.vm.eea.ui.motor.addPanelMotor

import com.vm.eea.application.CosPhi
import com.vm.eea.application.Efficiency
import com.vm.eea.ui.*
import com.vm.eea.utilities.CosPhi

data class UiState(
    //val input: WorkingVoltageField = WorkingVoltageField.validField(input = "415"),
    val power: PowerField = PowerField.empty(),
    val cosPhi: CosPhiField = CosPhiField.valid(value= CosPhi(.85)),
    val efficiency: EfficiencyField = EfficiencyField.valid(value = Efficiency(90)),
    val startMode: StartModeField = StartModeField.empty(),
    val demandFactor: CosPhiField = CosPhiField.valid(label = "Desired $CosPhi", CosPhi(.9)),
    val hasOverLoadProtection: BooleanField = BooleanField(label = "has Over load relay ?"),
    val isBidirectional: BooleanField = BooleanField(label = "Bidirectional ?"),
    val system :PowerSystemField = PowerSystemField.empty(),
    val protectionDevice: ProtectionTypeField = ProtectionTypeField.empty(label = "Protection device"),
    val ratedSpeed: SpeedField = SpeedField.empty(label = "Revolutions"),
    val canCalculate:Boolean=false,
) {

    val filedCount=10
    val progress:Int get() {
        var p=0
       // if(input.isValid) p+=1
        if(system.isValid) p+=1
        if(power.isValid) p+=1
        if(cosPhi.isValid) p+=1
        if(efficiency.isValid) p+=1
        if(startMode.isValid) p+=1
        if(demandFactor.isValid) p+=1
        if(hasOverLoadProtection.isValid) p+=1
        if(isBidirectional.isValid) p+=1
        if(protectionDevice.isValid) p+=1
        if(ratedSpeed.isValid) p+=1
        return p

    }

}