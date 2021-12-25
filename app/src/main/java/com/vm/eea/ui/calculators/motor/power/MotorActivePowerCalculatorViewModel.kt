package com.vm.eea.ui.calculators.motor.power

import androidx.lifecycle.ViewModel
import com.vm.eea.application.*
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

class MotorActivePowerCalculatorViewModel(
    private val calculator: ActivePowerCalculator
): ContainerHost<UiState,Nothing>,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState()){
        intent {
            reduce {
                state.copy(state=FormState.Calculating("waiting for input (${state.progress}/${state.filedCount})"))
            }
        }
    }





    fun onVoltageChange(value:String,system: PowerSystem)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val voltageState= state.voltage.copy(input=value,system = system,isValid = validationResult==null,error = validationResult)
        val newState=  state.copy(voltage = voltageState)
        calculate(newState)
    }

    fun onCurrentChange(value:String,unit:Current.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState= state.current.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState=  state.copy(current = fieldState)
        calculate(newState)
    }

    fun onCosPhiChanged(value: String)=intent{
        val validationResult=Validator.validate.inRange(value,.1,1.0,"")
        val newField= state.cosPhi.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState=  state.copy(cosPhi = newField)
        calculate(newState)
    }
    fun onEfficiencyChange(value: String)=intent {
        val validationResult=Validator.validate.inRange(value,1.0,100.0,"")
        val newField= state.efficiency.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState=  state.copy(efficiency = newField)
        calculate(newState)
    }


    private fun isValid(state: UiState):Boolean{
        if(state.current.notValid) return false
        if(state.voltage.notValid) return false
        if(state.efficiency.notValid) return false
        if(state.cosPhi.notValid) return false
        return true
    }

    private fun calculate(inputState:UiState)=intent{

        val isValid=isValid(inputState)
       val result= if(isValid){
            val voltage=inputState.voltage.voltage.value
            val cosPhi=inputState.cosPhi.value
            val system=inputState.voltage.system
            val current=inputState.current.value
           val efficiency=inputState.efficiency.efficiency
            val power= calculator(voltage,current,system,cosPhi,efficiency)
            FormState.Ready(power)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce { inputState.copy(state=result) }
    }



}