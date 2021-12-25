package com.vm.eea.ui.calculators.main.apparentPowerCalculator.activeReactive

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Power
import com.vm.eea.application.ReactivePower
import com.vm.eea.application.calculators.ApparentPowerCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ReactiveActivePowerApparentCalculatorViewModel(private val calculator: ApparentPowerCalculator
                                           ):ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
         =container( UiState())

     fun onActivePowerChange(value:String, unit: Power.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.activePower.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(activePower = fieldState)
        calculate(newState)
    }

     fun onReactivePowerChange(value:String, unit: ReactivePower.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.reactivePower.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(reactivePower = fieldState)
        calculate(newState)
    }

    private fun isValid(state: UiState):Boolean{
        if(state.reactivePower.notValid) return false
        if(state.activePower.notValid) return false
        return true
    }

    private fun calculate(inputState: UiState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){

            val reactivePower=inputState.reactivePower.value
            val activePower=inputState.activePower.value
            val power=calculator(activePower,reactivePower)
            FormState.Ready(power)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce { inputState.copy(state=result) }
    }




}