package com.vm.eea.ui.calculators.main.reactivePowerCalculator.typeA

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Current
import com.vm.eea.application.PowerSystem
import com.vm.eea.application.Voltage
import com.vm.eea.application.calculators.ReactivePowerCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ReactiveViewModelA(private val calculator:ReactivePowerCalculator):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState())

    fun onSystemChange(system: PowerSystem)=intent {

        val newState=  state.copy(system = system)
        calculate(newState)
    }

    fun onVoltageChange(value:String,unit: Voltage.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val voltageState= state.voltage.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState=  state.copy(voltage = voltageState)
        calculate(newState)
    }

    fun onCurrentChange(value:String,unit: Current.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState= state.current.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState=  state.copy(current = fieldState)
        calculate(newState)
    }

    fun onCosPhiChanged(value: String)=intent{
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val newField= state.cosPhi.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState=  state.copy(cosPhi = newField)
        calculate(newState)
    }

    private fun isValid(state: UiState):Boolean{
        if(state.current.notValid) return false
        if(state.voltage.notValid) return false
        return true
    }

    private fun calculate(inputState: UiState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){
            val voltage=inputState.voltage.value
            val system=inputState.system
            val current=inputState.current.value
            val cosPhi=inputState.cosPhi.value
            val power= calculator(voltage,current,cosPhi,system)
            FormState.Ready(power)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce { inputState.copy(state=result) }
    }
}