package com.vm.eea.ui.calculators.main.apparentPowerCalculator.powerCosPhi

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Power
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

class ActivePowerCosPhiApparentPowerViewModel(private val calculator: ApparentPowerCalculator):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState())

    fun onCosPhiChanged(value: String)=intent{
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val newField= state.cosPhi.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState=  state.copy(cosPhi = newField)
        calculate(newState)
    }
    fun onActivePowerChange(value:String, unit: Power.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.power.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(power = fieldState)
        calculate(newState)
    }

    private fun isValid(state: UiState):Boolean{
        if(state.power.notValid) return false
        if(state.cosPhi.notValid) return false
        return true
    }

    private fun calculate(inputState: UiState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){
            val activePower=inputState.power.value
            val cosPhi=inputState.cosPhi.value
            val power=calculator(activePower,cosPhi)
            FormState.Ready(power)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce { inputState.copy(state = result) }
    }
}