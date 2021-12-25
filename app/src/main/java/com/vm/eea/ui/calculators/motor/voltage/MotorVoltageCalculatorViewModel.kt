package com.vm.eea.ui.calculators.motor.voltage

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Current
import com.vm.eea.application.Power
import com.vm.eea.application.PowerSystem
import com.vm.eea.application.calculators.VoltageCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MotorVoltageCalculatorViewModel(private val calculator:VoltageCalculator):ContainerHost<UiState,Nothing>,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState())

    fun onPowerChange(value:String,unit: Power.Unit)=intent{
       val validationResult=Validator.validate.positiveNumber(value,"")
       val powerState=state.power.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
      val newState= state.copy(power = powerState)
       updateCalculationState(newState)
    }

    fun onCurrentChange(value:String,unit:Current.Unit)=intent{
        val validationResult=Validator.validate.positiveNumber(value,"")
        val voltageState=state.current.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(current = voltageState)
         updateCalculationState(newState)
    }

    fun onCosPhiChanged(value: String)=intent{
        val validationResult=Validator.validate.inRange(value,.1,1.0,"")
        val newField=state.cosPhi.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(cosPhi = newField)
        updateCalculationState(newState)
    }

    fun onEfficiencyChange(value:String)=intent{
        val validationResult=Validator.validate.inRange(value,1.0,100.0,"")
        val newField=state.efficiency.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(efficiency = newField)
        updateCalculationState(newState)
    }

    fun onSystemChange(system: PowerSystem)=intent {
        val newField=state.system.copy(value = system)
        val newState= state.copy(system = newField)
        updateCalculationState(newState)
    }

    private fun isValid(state:UiState):Boolean{
        if(state.power.notValid) return false
        if(state.current.notValid) return false
        if(state.efficiency.notValid) return false
        if(state.cosPhi.notValid) return false
        return true
    }

    private fun updateCalculationState(inputState:UiState)=intent{
        val isValid=isValid(inputState)
         if(isValid){
            val power=inputState.power.value
            val current=inputState.current.value
            val cosPhi=inputState.cosPhi.value
            val system=inputState.system.value
            val efficiency=inputState.efficiency.efficiency
            val ret=calculator(power,current,cosPhi,efficiency,system)

            reduce { inputState.copy(state = FormState.Ready(ret))}

        }else{
             reduce { inputState.copy(
                 state =FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})"))}
        }
    }




}


