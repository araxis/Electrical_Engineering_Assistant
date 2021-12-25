package com.vm.eea.ui.calculators.main.cosPhiCalculator.typeA

import androidx.lifecycle.ViewModel
import com.vm.eea.application.*
import com.vm.eea.application.calculators.CosPhiCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CosPhiViewModelA(private val calculator:CosPhiCalculator):ContainerHost<UiState,Nothing>,ViewModel() {
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

    fun onPowerChange(value:String,unit: Power.Unit)=intent{
        val validationResult=Validator.validate.positiveNumber(value,"")
        val powerState=state.power.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(power = powerState)
        calculate(newState)
    }

    private fun isValid(state: UiState):Boolean{
        if(state.current.notValid) return false
        if(state.voltage.notValid) return false
        if(state.power.notValid) return false
        return true
    }

    private fun calculate(inputState: UiState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){
            val voltage=inputState.voltage.value
            val power=inputState.power.value
            val system=inputState.system
            val current=inputState.current.value
            val cosPhi= calculator(power,voltage,current,system)
            FormState.Ready(cosPhi)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce { inputState.copy(state=result) }
    }

}