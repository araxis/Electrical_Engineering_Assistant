package com.vm.eea.ui.motor.motorFullupdate

import androidx.lifecycle.ViewModel
import com.vm.eea.application.*
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.motor.Motor
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.protectionDevice.ProtectionType
import com.vm.eea.ui.*
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.onIO
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorViewModel(private val motorId: MotorId,
                           private val motorRepository:IMotorRepository,
                           private val navigationManager: NavigationManager):ContainerHost<UiState,Nothing>,ViewModel() {
    private lateinit var motor: Motor
    override val container: Container<UiState, Nothing>
         = container(UiState()){
             onIO {
                 motor=motorRepository.getMotor(motorId)
                 intent {
                     val newState=mapToState()
                     reduce { newState }
                 }
             }
    }

    fun onPowerChange(value:String,unit: Power.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val powerState=state.power.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(power = powerState)
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
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val newField=state.cosPhi.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(cosPhi = newField)
        updateCalculationState(newState)
    }

    fun onEfficiencyChange(value:String)=intent{
        val validationResult= Validator.validate.inRange(value,1.0,100.0,"")

        val newField=state.efficiency.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(efficiency = newField)
        updateCalculationState(newState)
    }

    fun onOverRelayChange(value:Boolean)=intent{
        val newField=state.hasOverLoadProtection.copy(value=value)
        val newState= state.copy(hasOverLoadProtection = newField)
        updateCalculationState(newState)
    }

    fun onIsBiDirectChange(value:Boolean)=intent{

        val newField=state.isBidirectional.copy(value=value)
        val newState= state.copy(isBidirectional = newField)
        updateCalculationState(newState)
    }

    fun onDemandFactorChanged(value: String)=intent{
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")

        val newField=state.demandFactor.copy(input=value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(demandFactor = newField)
        updateCalculationState(newState)
    }

    fun onRatedSpeedChanged(value: String)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")

        val newField=state.ratedSpeed.copy(input= value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(ratedSpeed = newField)
        updateCalculationState(newState)
    }

    fun onProtectionTypeSelect(item: ProtectionType)=intent{

        val fieldState=state.protectionDevice.copy(value = item)
        val newState= state.copy(protectionDevice = fieldState)
        updateCalculationState(newState)
    }

    private fun mapToState(): UiState {

        return UiState(
            cosPhi = CosPhiField.valid("Power factor", value = motor.powerfactor),
            power = PowerField.valid("input", motor.power),
            startMode = StartModeField.empty("Start mode", motor.startMode),
            efficiency = EfficiencyField.valid(motor.efficiency),
            system = PowerSystemField.empty(value = motor.system),
            demandFactor = CosPhiField.valid("Demand factor", motor.demandFactor),
            ratedSpeed = SpeedField.valid("Rated speed", motor.ratedSpeed),
            hasOverLoadProtection = BooleanField("Over load relay", motor.hasOverLoadRelay),
            isBidirectional = BooleanField("Is bi direct", motor.isBiDirect),
            protectionDevice = ProtectionTypeField.empty("Protection type", motor.protectionType),
            canCalculate = true

        )
    }

    private fun isValid(state:UiState):Boolean{
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
        val system=state.system.value
        val speed=state.ratedSpeed.value
        val update=motor.copy(
            ratedSpeed = speed,
            startMode = startMode,
            demandFactor = demandFactor,
            system = system,
            efficiency = efficiency,
            power = power,
            hasOverLoadRelay = state.hasOverLoadProtection.value,
            isBiDirect = state.isBidirectional.value,
            powerfactor = powerfactor,
            protectionType = state.protectionDevice.value

        )
        onIO {
            motorRepository.update(update)
        }
        navigationManager.back()
    }

}