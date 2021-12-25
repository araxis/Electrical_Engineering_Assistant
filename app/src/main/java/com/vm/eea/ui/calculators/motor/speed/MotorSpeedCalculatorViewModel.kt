package com.vm.eea.ui.calculators.motor.speed

import androidx.lifecycle.ViewModel
import com.vm.eea.application.*
import com.vm.eea.application.calculators.MotorRealSpeedCalculator
import com.vm.eea.application.calculators.MotorSynchronousSpeedCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MotorSpeedCalculatorViewModel(private val synchronousSpeedCalculator:MotorSynchronousSpeedCalculator,
private val realSpeedCalculator: MotorRealSpeedCalculator):ContainerHost<UiState,Nothing>,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState()){
        intent {
            reduce {
                state.copy(state=FormState.Calculating("waiting for input (${state.progress}/${state.filedCount})"))
            }
        }
    }

    fun onPolesChange(value:String)=intent{
       val validationResult=Validator.validate.positiveNumber(value,"")
       val powerState=state.numberOfPoles.copy(value=value,isValid = validationResult==null,error = validationResult)
      val newState= state.copy(numberOfPoles = powerState)
       updateCalculationState(newState)
    }

    fun onFrequencyChange(value:String,unit:Frequency.Unit)=intent{
        val validationResult=Validator.validate.positiveNumber(value,"")
        val voltageState=state.frequency.copy(input= value,second = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(frequency = voltageState)
         updateCalculationState(newState)
    }

    fun onSlipFactorChanged(value: String)=intent{
        val validationResult=Validator.validate.inRange(value,1.0,100.0,"")
        val newField=state.slipFactor.copy(input= value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(slipFactor = newField)
        updateCalculationState(newState)
    }



    private fun isValid(state:UiState):Boolean{
        if(state.numberOfPoles.notValid) return false
        if(state.frequency.notValid) return false
        if(state.slipFactor.notValid) return false
        return true
    }

    private fun updateCalculationState(inputState:UiState)=intent{
        val isValid=isValid(inputState)
         if(isValid){
            val numberOfPoles=inputState.numberOfPoles.value.toInt()
            val frequency=inputState.frequency.value
            val slipFactor=inputState.slipFactor.value
            val syncSpeed=synchronousSpeedCalculator(frequency,numberOfPoles)
             val realSpeed=realSpeedCalculator(frequency,numberOfPoles,slipFactor)
            reduce { inputState.copy(state = FormState.Ready(SpeedResult(syncSpeed,realSpeed)))}

        }else{
             reduce { inputState.copy(
                 state =FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})"))}
        }
    }




}


