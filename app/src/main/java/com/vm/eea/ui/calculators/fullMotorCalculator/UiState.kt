package com.vm.eea.ui.calculators.fullMotorCalculator

import com.vm.eea.application.CosPhi
import com.vm.eea.application.Efficiency
import com.vm.eea.application.calculators.motorFullResult.MotorCalculationResult
import com.vm.eea.application.motor.MotorId
import com.vm.eea.ui.*
import com.vm.eea.ui.calculators.SubmitFormState
import com.vm.eea.utilities.CosPhi

data class UiState(
    val motorId:MotorId= MotorId(-1),
    val voltage:WorkingVoltageField= WorkingVoltageField.validField(value = "415"),
    val power: PowerField = PowerField.empty(),
    val cosPhi:CosPhiField = CosPhiField.valid(value= CosPhi(.85)),
    val efficiency:EfficiencyField = EfficiencyField.valid(value = Efficiency(90)),
    val startMode: StartModeField = StartModeField.empty(),
    val demandFactor:CosPhiField = CosPhiField.valid(label = "Desired $CosPhi",CosPhi(.9)),
    val hasOverLoadProtection:BooleanField= BooleanField(label = "has Over load relay ?"),
    val isBidirectional:BooleanField= BooleanField(label = "Bidirectional ?"),
    val protectionDevice:ProtectionTypeField = ProtectionTypeField.empty(label = "Protection device"),
    val ratedSpeed:SpeedField = SpeedField.empty(label = "Revolutions"),
    val canCalculate:Boolean=false,
    val formState:SubmitFormState<MotorCalculationResult> =SubmitFormState.Filling("waiting for input (8/10)"),
    val resultItems:List<GroupPropertyItem> = listOf()) {

    val filedCount=10
    val progress:Int get() {
        var p=0
        if(voltage.isValid) p+=1
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