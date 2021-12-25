package com.vm.eea.ui.calculators.main.activePowerCalculator.currentImpedance

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

class CurrentImpedanceActivePowerViewModel(
    private val calculator:ActivePowerCalculator
):ContainerHost<CurrentImpedanceState,Nothing>,ViewModel() {


    override val container: Container<CurrentImpedanceState, Nothing>
        = container(CurrentImpedanceState())


    fun onSystemChange(system: PowerSystem)=intent {

        val newState= state.copy(system = system)
        calculate(newState)
    }

     fun onCosPhiChanged(value: String)=intent{
        val validationResult=Validator.validate.inRange(value,.1,1.0,"")
        val newField= state.cosPhi.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(cosPhi = newField)
        calculate(newState)
    }
    fun onCurrentChange(value:String,unit: Current.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.current.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(current = fieldState)
        calculate(newState)
    }

    fun onImpedanceChange(value:String, unit:Impedance.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.impedance.copy(value=value,second = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(impedance = fieldState)
        calculate(newState)
    }

    private fun isValid(state: CurrentImpedanceState):Boolean{
        if(state.current.notValid) return false
        if(state.impedance.notValid) return false
        return true
    }

    private fun calculate(inputState: CurrentImpedanceState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){
            val system=inputState.system
            val current=inputState.current.value
            val impedance=inputState.impedance.impedance
            val cosPhi=inputState.cosPhi.value
            val power=calculator(system,impedance,current,cosPhi)
            FormState.Ready(power)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce {
            inputState.copy(state=result)
        }
    }



}