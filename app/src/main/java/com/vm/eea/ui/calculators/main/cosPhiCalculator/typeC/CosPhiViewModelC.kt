package com.vm.eea.ui.calculators.main.cosPhiCalculator.typeC

import androidx.lifecycle.ViewModel
import com.vm.eea.application.ApparentPower
import com.vm.eea.application.ReactivePower
import com.vm.eea.application.calculators.CosPhiCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CosPhiViewModelC(private val calculator: CosPhiCalculator
                                           ):ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
         =container( UiState())

     fun onApparentPowerChange(value:String, unit: ApparentPower.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.apparentPower.copy(value=value,second = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(apparentPower = fieldState)
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
        if(state.apparentPower.notValid) return false
        return true
    }

    private fun calculate(inputState: UiState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){

            val reactivePower=inputState.reactivePowerField.value
            val apparentPower=inputState.apparentPower.apparentPower
            val power=calculator(reactivePower,apparentPower)
            FormState.Ready(power)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce { inputState.copy(state=result) }
    }




}