package com.vm.eea.ui.calculators.main.activePowerCalculator.reactiveCosPhi

import androidx.lifecycle.ViewModel
import com.vm.eea.application.ReactivePower
import com.vm.eea.application.calculators.ActivePowerCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ReactivePowerCosPhiActivePowerViewModel(private val calculator: ActivePowerCalculator) :ContainerHost<ReactivePowerCosPhiState,Nothing> ,ViewModel() {

    override val container: Container<ReactivePowerCosPhiState, Nothing>
         = container(ReactivePowerCosPhiState())

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

    private fun isValid(state: ReactivePowerCosPhiState):Boolean{
        if(state.reactivePowerField.notValid) return false
        if(state.cosPhi.notValid) return false
        return true
    }

    private fun calculate(inputState: ReactivePowerCosPhiState)=intent{

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