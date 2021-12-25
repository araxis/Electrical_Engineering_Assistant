package com.vm.eea.ui.calculators.main.activePowerCalculator.apparentCosPhi

import androidx.lifecycle.ViewModel
import com.vm.eea.application.ApparentPower
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

class ApparentPowerCosPhiActivePowerViewModel(private val calculator:ActivePowerCalculator):ContainerHost<ApparentPowerCosPhiState,Nothing>,ViewModel() {

    override val container: Container<ApparentPowerCosPhiState, Nothing>
         = container(ApparentPowerCosPhiState())

     fun onCosPhiChanged(value: String)=intent{
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val newField= state.cosPhi.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState=  state.copy(cosPhi = newField)
        calculate(newState)
    }
     fun onApparentPowerChange(value:String, unit: ApparentPower.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.apparentPower.copy(value=value,second = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(apparentPower = fieldState)
        calculate(newState)
    }

    private fun isValid(state: ApparentPowerCosPhiState):Boolean{
        if(state.apparentPower.notValid) return false
        if(state.cosPhi.notValid) return false
        return true
    }

    private fun calculate(inputState: ApparentPowerCosPhiState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){
            val apparentPower=inputState.apparentPower.apparentPower
            val cosPhi=inputState.cosPhi.value
            val power=calculator(apparentPower,cosPhi)
            FormState.Ready(power)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce {
            inputState.copy(state = result)
        }

    }




}