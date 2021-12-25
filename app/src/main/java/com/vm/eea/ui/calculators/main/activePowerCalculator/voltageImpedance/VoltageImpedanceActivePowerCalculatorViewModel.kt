package com.vm.eea.ui.calculators.main.activePowerCalculator.voltageImpedance

import androidx.lifecycle.ViewModel
import com.vm.eea.application.*
import com.vm.eea.application.calculators.ActivePowerCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class VoltageImpedanceActivePowerCalculatorViewModel(
    private val calculator: ActivePowerCalculator
) : ContainerHost<VoltageImpedanceState,Nothing>,ViewModel() {

    override val container: Container<VoltageImpedanceState, Nothing>
         = container(VoltageImpedanceState())


    fun onSystemChange(system: PowerSystem)=intent {

        val newState= state.copy(system = system)
        calculate(newState)
    }

    fun onVoltageChange(value:String,unit: Voltage.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val voltageState=state.voltage.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(voltage = voltageState)
        calculate(newState)
    }

    fun onImpedanceChange(value:String,unit:Impedance.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.impedance.copy(value=value,second = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(impedance = fieldState)
        calculate(newState)
    }



    private fun isValid(state: VoltageImpedanceState):Boolean{
        if(state.impedance.notValid) return false
        if(state.voltage.notValid) return false
        if(state.cosPhi.notValid) return false
        return true
    }

    private fun calculate(inputState: VoltageImpedanceState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){
            val voltage=inputState.voltage.value
            val cosPhi=inputState.cosPhi.value
            val system=inputState.system
            val impedance=inputState.impedance.impedance
            val power=calculator(voltage,impedance,system,cosPhi)
            FormState.Ready(power)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce { inputState.copy(state = result) }
    }


}