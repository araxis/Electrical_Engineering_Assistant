package com.vm.eea.ui.calculators.fullMotorCalculator

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Power
import com.vm.eea.application.PowerSystem
import com.vm.eea.application.StartMode
import com.vm.eea.application.calculators.applicationProject.motorProject.ApplicationMotor
import com.vm.eea.application.calculators.applicationProject.motorProject.ApplicationMotorUpdater
import com.vm.eea.application.calculators.applicationProject.motorProject.IGetApplicationMotor
import com.vm.eea.application.format
import com.vm.eea.application.protectionDevice.ProtectionType
import com.vm.eea.ui.*
import com.vm.eea.ui.calculators.SubmitFormState
import com.vm.eea.utilities.CosPhi
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FullMotorCalculatorFormViewModel(private val applicationMotorUpdater: ApplicationMotorUpdater,
                                       private val navigationManager: NavigationManager,
                                       private val getApplicationMotor: IGetApplicationMotor
):ContainerHost<UiState,Nothing>,ViewModel() {

    private lateinit var _motor: ApplicationMotor

    override val container: Container<UiState, Nothing>
         = container(UiState()){
             intent {
                 _motor=getApplicationMotor()
                 val uiState=mapToState(_motor)
                 updateCalculationState(uiState)
             }
    }

    fun onPowerChange(value:String,unit: Power.Unit)=intent{
        val validationResult=Validator.validate.positiveNumber(value,"")
        val powerState=state.power.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(power = powerState)
        updateCalculationState(newState)
    }

    fun onStartModeSelect(startMode: StartMode)=intent{
        val startModeState=state.startMode.copy(value = startMode)
        val newState= state.copy(startMode = startModeState)
        updateCalculationState(newState)


    }

    fun onVoltageChange(value:String,system:PowerSystem)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val voltageState=state.voltage.copy(input= value,system = system,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(voltage = voltageState)
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



    private fun isValid(state:UiState):Boolean{
        if(state.power.notValid) return false
        if(state.voltage.notValid) return false
        if(state.efficiency.notValid) return false
        if(state.cosPhi.notValid) return false
        if(state.demandFactor.notValid) return false
        if(state.ratedSpeed.notValid) return false

        return true
    }

    private fun updateCalculationState(newState: UiState)=intent {
        val canCalculate=isValid(newState)
        val formState=newState.copy(canCalculate = canCalculate)
        if(!canCalculate) {
            reduce { formState.copy(formState = SubmitFormState.Filling("waiting for input (${state.progress}/${state.filedCount})"))}
        }else{
            reduce { formState.copy(formState=SubmitFormState.ReadyToCalculate()) }
        }
    }

    private fun mapToState(motor: ApplicationMotor):UiState {
        val newUiState=UiState(
            motorId=motor.motorId,
            protectionDevice = ProtectionTypeField.empty("Protection device",motor.protectionType),
            ratedSpeed = SpeedField.valid("Rated speed",motor.ratedSpeed.value.format()),
            demandFactor = CosPhiField.valid("Required $CosPhi",motor.demandFactor),
            isBidirectional = BooleanField("Is bi direct",motor.isBiDirect),
            hasOverLoadProtection = BooleanField("Over load relay",motor.hasOverLoadRelay),
            startMode = StartModeField.empty("Start mode",motor.startMode),
            cosPhi = CosPhiField.valid(CosPhi,motor.powerfactor),
            efficiency = EfficiencyField.valid(motor.efficiency),
            power = PowerField.valid("Power",motor.power),
            voltage = WorkingVoltageField.validField("Voltage",motor.voltage,motor.system)
        )
        val isValid=isValid(newUiState)
        return if(!isValid) {
            newUiState.copy(formState = SubmitFormState.Filling("waiting for input (${newUiState.progress}/${newUiState.filedCount})"))
        }else{
            newUiState.copy(formState = SubmitFormState.ReadyToCalculate())
        }

    }

    fun showResult()=intent {
        val power=state.power.value
        val voltage=state.voltage.voltage.value
        val startMode=state.startMode.value
        val efficiency=state.efficiency.efficiency
        val cosPhi=state.cosPhi.value
        val demandFactor=state.demandFactor.value
        val overLoadRelay=state.hasOverLoadProtection.value
        val isBiDirect=state.isBidirectional.value
        val ratedSpeed=state.ratedSpeed.value
        val protectionType=state.protectionDevice.value
        val system=state.voltage.voltage.system
        val updateMotor=_motor.copy(power=power,voltage = voltage,
            startMode = startMode,
            efficiency = efficiency,
            powerfactor = cosPhi,
            demandFactor = demandFactor,
            hasOverLoadRelay = overLoadRelay,
            isBiDirect = isBiDirect,ratedSpeed = ratedSpeed,protectionType = protectionType,system = system
            )
         applicationMotorUpdater.update(updateMotor)
        navigationManager.navigate(MotorCalculatorDestinations.FullMotorResult(state.motorId))

    }







}