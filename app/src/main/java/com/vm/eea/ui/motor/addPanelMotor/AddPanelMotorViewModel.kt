package com.vm.eea.ui.motor.addPanelMotor

import androidx.lifecycle.ViewModel
import com.vm.eea.application.*
import com.vm.eea.application.motor.addMotor.AddMotor
import com.vm.eea.application.protectionDevice.ProtectionType
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddPanelMotorViewModel(
    private val panelId: PanelId,
    private val addMotor: AddMotor,
    private val navigationManager: NavigationManager):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState())

    fun onPowerChange(value:String,unit: Power.Unit)=intent{
        val newState= state.copy(power = state.power.validate(value, unit))
        updateCalculationState(newState)
    }

    fun onStartModeSelect(startMode: StartMode)=intent{
        val startModeState=state.startMode.copy(value = startMode)
        val newState= state.copy(startMode = startModeState)
        updateCalculationState(newState)
    }

    fun onSystemSelect(item: PowerSystem)=intent{
        val fieldState=state.system.copy(value = item)
        val newState= state.copy(system = fieldState)
        updateCalculationState(newState)
    }

    fun onCosPhiChanged(value: String)=intent{
        val newState= state.copy(cosPhi = state.cosPhi.validate(value))
        updateCalculationState(newState)
    }

    fun onEfficiencyChange(value:String)=intent{
        val newState= state.copy(efficiency = state.efficiency.validate(value))
        updateCalculationState(newState)
    }

    fun onOverRelayChange(value:Boolean)=intent{
        val newState= state.copy(hasOverLoadProtection = state.hasOverLoadProtection.copy(value=value))
        updateCalculationState(newState)
    }

    fun onIsBiDirectChange(value:Boolean)=intent{
        val newState= state.copy(isBidirectional = state.isBidirectional.copy(value=value))
        updateCalculationState(newState)
    }

    fun onDemandFactorChanged(value: String)=intent{
        val newState= state.copy(demandFactor = state.demandFactor.validate(value))
        updateCalculationState(newState)
    }

    fun onRatedSpeedChanged(value: String)=intent{
        val newState= state.copy(ratedSpeed = state.ratedSpeed.validate(value))
        updateCalculationState(newState)
    }

    fun onProtectionTypeSelect(item: ProtectionType)=intent{

        val fieldState=state.protectionDevice.copy(value = item)
        val newState= state.copy(protectionDevice = fieldState)
        updateCalculationState(newState)
    }


    private fun isValid(state: UiState):Boolean{
        if(state.power.notValid) return false
       // if(state.input.notValid) return false
        if(state.efficiency.notValid) return false
        if(state.cosPhi.notValid) return false
        if(state.demandFactor.notValid) return false
        if(state.ratedSpeed.notValid) return false

        return true
    }

    private fun updateCalculationState(newState: UiState)=intent {
        val canCalculate=isValid(newState)
        val formState=newState.copy(canCalculate = canCalculate)
         reduce { formState }
    }

     fun submit()=intent {
        val startMode=state.startMode.value
        val power=state.power.input be  state.power.unit
        val powerfactor= state.cosPhi.value
        val demandFactor= state.demandFactor.value
        val efficiency= state.efficiency.efficiency
        val feedingMode= FeedingMode(normal = true, emergency = false)
         val system=state.system.value
        addMotor(power,powerfactor,demandFactor,
            false,efficiency, Speed(1200),
            isBiDirect = false,
            hasOverloadRelay = false,
            protectionType = ProtectionType.CircuitBreaker,
            system = system,
            startMode = startMode,
            feedingMode = feedingMode,
            feederId = panelId,
            feedLineLength = Length(10,Length.Unit.M))
        navigationManager.back()
    }


}