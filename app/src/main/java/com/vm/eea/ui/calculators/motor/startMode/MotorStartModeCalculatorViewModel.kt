package com.vm.eea.ui.calculators.motor.startMode

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Power
import com.vm.eea.application.calculators.StartModeCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MotorStartModeCalculatorViewModel(private val calculator:StartModeCalculator):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState())
    fun onPowerChange(value:String,unit: Power.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val powerState=state.power.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(power = powerState)
        updateCalculationState(newState)
    }


    private fun isValid(state: UiState):Boolean{
        if(state.power.notValid) return false
        return true
    }

    private fun updateCalculationState(inputState: UiState)=intent{
        val isValid=isValid(inputState)
        if(isValid){
            val power=inputState.power.value
            val startMode=calculator(power)
            reduce { inputState.copy(state = FormState.Ready(startMode))}

        }else{
            reduce { inputState.copy(
                state = FormState.Failed("waiting for input"))}
        }
    }
}