package com.vm.eea.ui.calculators.motor.torque

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Power
import com.vm.eea.application.calculators.TorqueCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MotorTorqueCalculatorViewModel(private val calculator:TorqueCalculator):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState()){
        intent {
            reduce {
                state.copy(state=FormState.Calculating("waiting for input (${state.progress}/${state.filedCount})"))
            }
        }
    }

    fun onSpeedChange(value:String)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val newField=state.speed.copy(input= value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(speed = newField)
        calculate(newState)
    }

    fun onPowerChange(value:String,unit: Power.Unit)=intent{
        val validationResult=Validator.validate.positiveNumber(value,"")
        val powerState=state.power.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(power = powerState)
        calculate(newState)
    }

    private fun isValid(state: UiState):Boolean{
        if(state.speed.notValid) return false
        if(state.power.notValid) return false
        return true
    }

    private fun calculate(inputState: UiState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){
            val speed=inputState.speed.value
            val power=inputState.power.value
            val ret= calculator(power,speed)
            FormState.Ready(ret)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce { inputState.copy(state=result) }
    }
}