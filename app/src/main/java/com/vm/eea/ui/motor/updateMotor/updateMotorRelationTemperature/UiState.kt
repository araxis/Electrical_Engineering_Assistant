package com.vm.eea.ui.motor.updateMotor.updateMotorRelationTemperature


import com.vm.eea.application.Environment
import com.vm.eea.application.UnitOfTemperature
import com.vm.eea.ui.Field
import com.vm.eea.ui.TemperatureField


data class UiState(val pageTitle:String, val temperature:TemperatureField= Field.empty(
    UnitOfTemperature.C), val canSubmit:Boolean=false) {

    companion object{
        fun init(environment: Environment)=when(environment){
            Environment.Ambient -> UiState("Ambient temperature",
                Field.empty(UnitOfTemperature.C),
                false
                )
            Environment.Ground -> UiState("Ground temperature",
                Field.empty(UnitOfTemperature.C),
                false
                )
        }
    }
}