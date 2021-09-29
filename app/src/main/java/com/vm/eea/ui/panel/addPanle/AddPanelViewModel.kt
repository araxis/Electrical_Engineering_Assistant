package com.vm.eea.ui.panel.addPanle

import androidx.lifecycle.ViewModel
import com.vm.eea.application.CosPhi
import com.vm.eea.application.Length
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.be
import com.vm.eea.application.panel.IGetSimplePanels
import com.vm.eea.application.panel.SimplePanel
import com.vm.eea.application.panel.addPanel.AddPanel
import com.vm.eea.application.project.ProjectId
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.*
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddPanelViewModel(
    private val projectId: ProjectId,
    private val getPanels: IGetSimplePanels,
    private val addPanel: AddPanel,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Effect>,ViewModel() {

    override val container: Container<UiState, Effect> = container(UiState.init()){
       onIO {
           val feeders=getPanels(projectId)
           intent { reduce { state.copy(feeders = feeders.map { SelectableItem(it,false) }) } }
       }
    }
    fun onCodeChange(value:String)=intent{

        val codeValidation=Validator.validate.notNullOrBlank(value,"")
        val newState=state.copy(code=value,
            codeError = codeValidation?.let { SimpleText(it.message) })
        reduce {
            newState.copy(canSubmit = canSubmit(newState))
        }
    }

    fun onDescriptionChange(value:String)=intent{
        reduce { state.copy(description = value) }
    }

    fun onDemandFactorChange(value:String)=intent{
        val validationResult=Validator.validate.inRange(value,.1,1.0,"")
        val demandFactor=state.demandFactor.copy(value=value,error = validationResult)
        val newState=state.copy(demandFactor = demandFactor)
        reduce { state.copy(demandFactor = demandFactor,canSubmit = canSubmit(newState)) }
    }


    fun onLengthChange(value:String,unit: Length.Unit)=intent{
      val lengthValidation=Validator.validate.positiveNumber(value,"")
      val newState=  state.copy(length = value,lengthUnit= unit,
            lengthError = lengthValidation?.let { SimpleText(it.message) })
        reduce { newState.copy(canSubmit = canSubmit(newState)) }
    }

    fun onChangeFeeder(feeder: SimplePanel)=intent{
        val newState= state.copy(feeder=feeder)
        reduce { newState.copy(canSubmit = canSubmit(newState))}
    }


    fun onSubmit()=intent{
            val demandFactor= CosPhi(state.demandFactor.value.toDouble())
            val length=state.length be  Length.Unit.M
            addPanel(state.code,state.description,demandFactor,state.feeder!!.id ,length)
             navigationManager.back()
    }

    fun onFeederSelectRequest()=intent{
        postSideEffect(Effect.ShowModal())
    }

    fun canSubmit(state:UiState):Boolean{
        if(state.feeder==null) return false
        if(Validator.validate.notNullOrBlank(state.code,"") !=null)return false
        if(Validator.validate.inRange(state.demandFactor.value,.1,1.0,"")!=null)return false
        if(Validator.validate.positiveNumber(state.length,"")!=null)return false
        return true
    }
}