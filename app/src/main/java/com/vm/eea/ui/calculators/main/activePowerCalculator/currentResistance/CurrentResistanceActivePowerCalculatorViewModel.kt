package com.vm.eea.ui.calculators.main.activePowerCalculator.currentResistance

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Current
import com.vm.eea.application.PowerSystem
import com.vm.eea.application.Resistance
import com.vm.eea.application.calculators.ActivePowerCalculator
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CurrentResistanceActivePowerCalculatorViewModel(
    private val calculator: ActivePowerCalculator
): ContainerHost<CurrentResistanceState,Nothing> ,ViewModel() {

    override val container: Container<CurrentResistanceState, Nothing>
         = container(CurrentResistanceState())

    fun onSystemChange(system: PowerSystem)=intent {

        val newState= state.copy(system = system)
        calculate(newState)
    }

    fun onCurrentChange(value:String,unit: Current.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.current.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(current = fieldState)
        calculate(newState)
    }

    fun onResistanceChange(value:String,unit:Resistance.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val fieldState=state.resistance.copy(value=value,second = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(resistance = fieldState)
        calculate(newState)
    }

    private fun isValid(state: CurrentResistanceState):Boolean{
        if(state.current.notValid) return false
        if(state.resistance.notValid) return false

        return true
    }

    private fun calculate(inputState: CurrentResistanceState)=intent{

        val isValid=isValid(inputState)
        val result= if(isValid){
            val system=inputState.system
            val current=inputState.current.value
            val resistance=inputState.resistance.resistance
            val power=calculator(system,resistance,current)
            FormState.Ready(power)
        }else{
            FormState.Failed("waiting for input (${inputState.progress}/${inputState.filedCount})")
        }
        reduce { inputState.copy(state=result) }
    }


}