package com.vm.eea.ui.calculators.motor.slip

import androidx.lifecycle.ViewModel
import com.vm.eea.application.calculators.*
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.positiveNumber
import com.vm.eea.utilities.smallerOrEqual
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MotorSlipCalculatorViewModel(
    private val slipFactorCalculator: MotorSlipFactorCalculator,
    private val slipSpeedCalculator: MotorSlipSpeedCalculator,
):ContainerHost<UiState,Nothing>,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState()){
        intent {
            reduce {
                state.copy(state=FormState.Calculating("waiting for input (${state.progress}/${state.filedCount})"))
            }
        }
    }



    fun onSyncSpeedChange(value:String)=intent{
        val validationResult=Validator.validate.positiveNumber(value,"")
        val newField=state.syncSpeed.copy(input= value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(syncSpeed = newField)
        updateCalculationState(newState)
    }

    fun onRotorSpeedChange(value:String)=intent{
        val validationResult=Validator.validate.smallerOrEqual(value,state.syncSpeed.value.value,"")
        val newField=state.rotorSpeed.copy(input= value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(rotorSpeed = newField)
        updateCalculationState(newState)
    }



    private fun isValid(state:UiState):Boolean{
        if(state.rotorSpeed.notValid) return false
        if(state.syncSpeed.notValid) return false
        return true
    }

    private fun updateCalculationState(inputState:UiState)=intent{
        val isValid=isValid(inputState)
         if(isValid){
            val syncSpeed=inputState.syncSpeed.value
            val rotorSpeed=inputState.rotorSpeed.value
           val slipFactor=slipFactorCalculator(syncSpeed,rotorSpeed)
            val slipSpeed=slipSpeedCalculator(syncSpeed,rotorSpeed)

            reduce { inputState.copy(state = FormState.Ready(SlipResult(slipFactor,slipSpeed)))}

        }else{
             reduce { inputState.copy(
                 state =FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})"))}
        }
    }




}


