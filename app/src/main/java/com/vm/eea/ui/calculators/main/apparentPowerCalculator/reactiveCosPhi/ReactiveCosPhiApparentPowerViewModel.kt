package com.vm.eea.ui.calculators.main.apparentPowerCalculator.reactiveCosPhi

import androidx.lifecycle.ViewModel
import com.vm.eea.application.ReactivePower
import com.vm.eea.application.calculators.ApparentPowerCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ReactiveCosPhiApparentPowerViewModel(private val calculator: ApparentPowerCalculator) :ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
         = container(UiState())

     fun onCosPhiChanged(value: String)=intent{
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val newField= state.cosPhi.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState=  state.copy(cosPhi = newField)
        calculate(newState)
    }
     fun onReactivePowerChange(value:String, unit: ReactivePower.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.reactivePowerField.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(reactivePowerField = fieldState)
        calculate(newState)
    }

    private fun isValid(state: UiState):Boolean{
        if(state.reactivePowerField.notValid) return false
        if(state.cosPhi.notValid) return false
        return true
    }

    private fun calculate(inputState: UiState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){

            val reactivePower=inputState.reactivePowerField.value
            val cosPhi=inputState.cosPhi.value
            val power=calculator(reactivePower,cosPhi)
            FormState.Ready(power)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce { inputState.copy(state = result) }
    }




}