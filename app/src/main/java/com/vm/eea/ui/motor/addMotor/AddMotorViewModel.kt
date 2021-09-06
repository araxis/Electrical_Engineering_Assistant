package com.vm.eea.ui.motor.addMotor

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.*
import com.vm.eea.domain.load.AddNewMotor
import com.vm.eea.domain.load.FeedingMode
import com.vm.eea.domain.panel.PanelId
import com.vm.eea.domain.project.GetProject
import com.vm.eea.ui.Field
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.SelectableItem
import com.vm.eea.ui.models.GetSimplePanels
import com.vm.eea.ui.models.SimplePanel
import com.vm.eea.utilities.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddMotorViewModel(
    private val projectId: Long,
    private val getSimplePanels: GetSimplePanels,
    private val getProject: GetProject,
    private val addNewMotor: AddNewMotor,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Effect>,ViewModel() {
    override val container: Container<UiState, Effect>
         = container(UiState()){
             intent {
                 getProject(projectId).combine(getSimplePanels(projectId)){project,panels->
                     state.copy(
                         feeders =panels.map {i-> SelectableItem(i,false) },
                         powerfactor = Field(project.threePhasePowerFactor(),"",null),
                         demandFactor = Field(project.threePhasePowerFactor(),"",null)
                     )
                 }.collect {
                     reduce { it }
                 }
             }
    }

    fun onCodeChange(value:String)=intent{
        val validationResult=Validator.validate.notNullOrBlank(value,"")
        val tempState=state.code.copy(value=value,error = validationResult)
        val newState=state.copy(code = tempState)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }
    fun onPowerChange(value:String,unit:UnitOfPower)=intent{
        val validationResult=Validator.validate.positiveNumber(value,"")
        val tempState=state.power.copy(value=value,error = validationResult)
        val newState=state.copy(power = tempState)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }
    fun onPowerfactorChange(value:String)=intent{
        val validationResult=Validator.validate.inRange(value,.1,1.0,"")
        val tempState=state.powerfactor.copy(value=value,error = validationResult)
        val newState=state.copy(powerfactor = tempState)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }
    fun onEfficiencyChange(value:String)=intent{
        val validationResult=Validator.validate.inRange(value,1.0,100.0,"")
        val tempState=state.efficiency.copy(value=value,error = validationResult)
        val newState=state.copy(efficiency = tempState)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }
    fun onDemandFactorChange(value:String)=intent{
        val validationResult=Validator.validate.inRange(value,.1,1.0,"")
        val tempState=state.demandFactor.copy(value=value,error = validationResult)
        val newState=state.copy(demandFactor = tempState)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }
    fun onFeederSelect(feeder: SimplePanel)=intent{
        val newState=state.copy(feeder = feeder)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }

    fun onSystemSelect(system:PowerSystem)=intent{
        val newState=state.copy(system = system)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }

    fun onFeederSelectRequest()=intent{
        reduce { state.copy(selector = UiState.Selector.Feeder) }
        postSideEffect(Effect.Toast("a"))
    }
    fun onSystemSelectRequest()=intent{
        reduce { state.copy(selector = UiState.Selector.System) }
        postSideEffect(Effect.Toast("b"))
    }
    private fun canSubmit(state: UiState):Boolean{
        if(state.feeder==null) return false
        if(Validator.validate.notNullOrBlank(state.code.value,"") !=null)return false
         if(Validator.validate.positiveNumber(state.power.value,"")!=null)return false
         if(Validator.validate.inRange(state.efficiency.value,.1,100.0,"")!=null)return false
         if(Validator.validate.inRange(state.powerfactor.value,.1,1.0,"")!=null)return false
         if(Validator.validate.positiveNumber(state.feedLength.value,"")!=null)return false
         if(Validator.validate.inRange(state.demandFactor.value,.1,1.0,"")!=null)return false
        return true
    }

    fun onLengthChange(value: String, unit: UnitOfLength)=intent {
        val validationResult=Validator.validate.positiveNumber(value,"")
        val tempState=state.feedLength.copy(value=value,second=unit,error = validationResult)
        val newState=state.copy(feedLength = tempState)
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }

    fun submit()=intent {
        val code=state.code.value
        val system=state.system
        val power=state.power.value be  state.power.second
        val length=state.feedLength.value be state.feedLength.second
        val powerfactor= PowerFactor(state.powerfactor.value.toDouble())
        val demandFactor=PowerFactor(state.demandFactor.value.toDouble())
        val efficiency=Efficiency(state.efficiency.value.toDouble())
        val feederId= PanelId(state.feeder!!.id)
        val feedingMode=FeedingMode(normal = true, emergency = false)
         addNewMotor(code,"",power,powerfactor,
             demandFactor,efficiency,system,feedingMode,length,feederId)
        navigationManager.back()
    }


}