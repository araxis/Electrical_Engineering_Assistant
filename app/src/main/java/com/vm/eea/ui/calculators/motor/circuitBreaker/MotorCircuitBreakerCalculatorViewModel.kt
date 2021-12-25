package com.vm.eea.ui.calculators.motor.circuitBreaker

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Power
import com.vm.eea.application.PowerSystem
import com.vm.eea.application.Voltage
import com.vm.eea.application.calculators.MotorCircuitBreakerCalculator
import com.vm.eea.application.protectionDevice.ProtectionType
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MotorCircuitBreakerCalculatorViewModel(private val calculator: MotorCircuitBreakerCalculator):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState()){
             intent {
                 reduce {
                     state.copy(state=FormState.Calculating("waiting for input (${state.progress}/${state.filedCount})"))
                 }
             }
    }

    fun onPowerChange(value:String,unit: Power.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val powerState=state.power.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(power = powerState)
        updateCalculationState(newState)
    }

    fun onVoltageChange(value:String,system: PowerSystem)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val voltageState=state.voltage.copy(input=value,system = system,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(voltage = voltageState)
        updateCalculationState(newState)
    }

    fun onCosPhiChanged(value: String)=intent{
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val newField=state.cosPhi.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(cosPhi = newField)
        updateCalculationState(newState)
    }

    fun onProtectionTypeChange(type: ProtectionType)=intent {
        val newField=state.protectionType.copy(value = type)
        updateCalculationState(state.copy(protectionType = newField))
    }

    fun onEfficiencyChange(value:String)=intent{
        val validationResult= Validator.validate.inRange(value,1.0,100.0,"")
        val newField=state.efficiency.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(efficiency = newField)
        updateCalculationState(newState)
    }


    private fun isValid(state:UiState):Boolean{
        if(state.power.notValid) return false
        if(state.voltage.notValid) return false
        if(state.efficiency.notValid) return false
        if(state.cosPhi.notValid) return false
        return true
    }

    private fun updateCalculationState(inputState:UiState)=intent{
        val isValid=isValid(inputState)
        if(isValid){
            val power=inputState.power.value
            val voltage=inputState.voltage.voltage.value
            val cosPhi=inputState.cosPhi.value
            val system=inputState.voltage.system
            val efficiency=inputState.efficiency.efficiency
            val protectionType=state.protectionType.value
            val result=calculator(voltage,power,system,cosPhi,efficiency,protectionType,false)

            reduce { inputState.copy(state = FormState.Ready(result))}

        }else{
            reduce { inputState.copy(
                state = FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})"))}
        }
    }


}