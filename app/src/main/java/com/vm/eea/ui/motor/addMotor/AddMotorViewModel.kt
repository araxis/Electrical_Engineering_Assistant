package com.vm.eea.ui.motor.addMotor

import androidx.lifecycle.ViewModel
import com.vm.eea.application.*
import com.vm.eea.application.calculators.StartModeCalculator
import com.vm.eea.application.motor.addMotor.AddMotor
import com.vm.eea.application.panel.IGetSimplePanels
import com.vm.eea.application.panel.SimplePanel
import com.vm.eea.application.project.ProjectId
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.notNullOrBlank
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddMotorViewModel(
    private val projectId: ProjectId,
    private val getSimplePanels: IGetSimplePanels,
    private val addNewMotor: AddMotor,
    private val startModeCalculator: StartModeCalculator,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Effect>,ViewModel() {
    override val container: Container<UiState, Effect>
         = container(UiState()){

    }

    fun onCodeChange(value:String)=intent{
        val validationResult=Validator.validate.notNullOrBlank(value,"")
        val tempState=state.code.copy(value=value,error = validationResult)
        val newState=state.copy(code = tempState)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }
    fun onPowerChange(value:String,unit: Power.Unit)=intent{
        val validationResult=Validator.validate.positiveNumber(value,"")
        val powerValue= value be unit
        val startMode=startModeCalculator(powerValue)
        val tempState=state.power.copy(value=value,second = unit,error = validationResult)
        val newState=state.copy(power = tempState,startMode = startMode)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }
    fun onPowerfactorChange(value: CosPhi)=intent{
       val newState=state.copy(powerfactor = value)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }
    fun onEfficiencyChange(value: Efficiency)=intent{

        val newState=state.copy(efficiency = value)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }
    fun onFeederSelect(feeder: SimplePanel)=intent{
        val newState=state.copy(feeder = feeder)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }
    fun onSystemSelect(system: PowerSystem)=intent{
        val newState=state.copy(system = system)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }
    fun onStartModeSelect(startMode: StartMode)=intent{
        val newState=state.copy(startMode = startMode)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }

    fun onFeederSelectRequest()=intent{

        val feeders=getSimplePanels(projectId).map { SelectableItem(it,false) }
        reduce { state.copy(selector = UiState.Selector.Feeder,feeders =feeders ) }
        postSideEffect(Effect.ShowModal())
    }
    fun onSystemSelectRequest()=intent{
        reduce { state.copy(selector = UiState.Selector.System) }
        postSideEffect(Effect.ShowModal())
    }

    fun onSTartModeSelectRequest()=intent{
        reduce { state.copy(selector = UiState.Selector.StartMode) }
        postSideEffect(Effect.ShowModal())
    }

    private fun canSubmit(state: UiState):Boolean{
        if(state.feeder==null) return false
         if(Validator.validate.notNullOrBlank(state.code.value,"") !=null)return false
         if(Validator.validate.positiveNumber(state.power.value,"")!=null)return false
         if(Validator.validate.inRange(state.efficiency.value,.1,100.0,"")!=null)return false
         if(Validator.validate.inRange(state.powerfactor.value,.1,1.0,"")!=null)return false
         if(Validator.validate.positiveNumber(state.feedLength.value,"")!=null)return false

        return true
    }

    fun onLengthChange(value: String, unit: Length.Unit)=intent {
        val validationResult=Validator.validate.positiveNumber(value,"")
        val tempState=state.feedLength.copy(value=value,second=unit,error = validationResult)
        val newState=state.copy(feedLength = tempState)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }

    fun submit()=intent {
        val code=state.code.value
        val system=state.system
        val startMode=state.startMode
        val power=state.power.value be  state.power.second
        val length=state.feedLength.value be state.feedLength.second
        val powerfactor= CosPhi(state.powerfactor.value)
        val demandFactor= CosPhi(state.powerfactor.value)
        val efficiency= Efficiency(state.efficiency.value)
        val feederId= state.feeder!!.id
        val feedingMode= FeedingMode(normal = true, emergency = false)
        addNewMotor(code,"",power,powerfactor,demandFactor,false,efficiency,system,
        startMode,feedingMode,feederId,length)
        navigationManager.back()
    }


}