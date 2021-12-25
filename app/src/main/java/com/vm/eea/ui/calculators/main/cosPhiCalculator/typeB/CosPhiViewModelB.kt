package com.vm.eea.ui.calculators.main.cosPhiCalculator.typeB

import androidx.lifecycle.ViewModel
import com.vm.eea.application.ApparentPower
import com.vm.eea.application.Power
import com.vm.eea.application.calculators.CosPhiCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CosPhiViewModelB(private val calculator: CosPhiCalculator):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState())

    fun onActivePowerChange(value:String, unit: Power.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.activePower.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(activePower = fieldState)
        calculate(newState)
    }

    fun onApparentPowerChange(value:String, unit: ApparentPower.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.apparentPower.copy(value=value,second = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(apparentPower = fieldState)
        calculate(newState)
    }

    private fun isValid(state:UiState):Boolean{
        if(state.apparentPower.notValid) return false
        if(state.activePower.notValid) return false
        return true
    }

    private fun calculate(inputState: UiState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){

            val apparentPower=inputState.apparentPower.apparentPower
            val activePower=inputState.activePower.value
            val power=calculator(activePower,apparentPower)
            FormState.Ready(power)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce { inputState.copy(state=result) }
    }

}