package com.vm.eea.ui.calculators.main.startModeCalculator

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

class StartModeCalculatorViewModel(private val calculator:StartModeCalculator):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState())

    fun onActivePowerChange(value:String, unit: Power.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.activePower.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(activePower = fieldState)
        calculate(newState)
    }

    private fun isValid(state: UiState):Boolean{
        if(state.activePower.notValid) return false
        return true
    }

    private fun calculate(inputState:UiState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){


            val activePower=inputState.activePower.value
            val result=calculator(activePower)
            FormState.Ready(result)
        }else{
            FormState.Failed("waiting for input")
        }
        reduce { inputState.copy(state=result) }
    }
}