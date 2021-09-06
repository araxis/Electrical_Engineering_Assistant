package com.vm.eea.ui.panel.addPanle

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.UnitOfLength
import com.vm.eea.domain.panel.AddPanel
import com.vm.eea.domain.panel.GetMdp
import com.vm.eea.domain.panel.GetPanels
import com.vm.eea.domain.panel.Panel
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.*
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddPanelViewModel(
    private val projectId:Long,
    private val getMdp: GetMdp,
    private val getPanels: GetPanels,
    private val addPanel: AddPanel,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {

    override val container: Container<UiState, Nothing> = container(UiState.init()){
        intent {
            val mdp=getMdp(projectId)
            getPanels(projectId).collect {
                reduce { state.copy(feeder = mdp,feeders = it) }
            }

        }
    }
    fun onCodeChange(value:String)=intent{
        val lengthValidation=Validator.validate.positiveNumber(state.length,"")
        val codeValidation=Validator.validate.notNullOrBlank(value,"")
        val canSubmit =lengthValidation==null && codeValidation==null
        reduce {
            state.copy(code=value,
                codeError = codeValidation?.let { SimpleText(it.message) },
            canSubmit = canSubmit)
        }
    }

    fun onDescriptionChange(value:String)=intent{
        reduce { state.copy(description = value) }
    }

    fun onLengthChange(value:String,unit:UnitOfLength)=intent{
      val lengthValidation=Validator.validate.positiveNumber(value,"")
      val codeValidation=Validator.validate.notNullOrBlank(state.code,"")
      val canSubmit =lengthValidation==null && codeValidation==null

        reduce { state.copy(length = value,
            lengthError = lengthValidation?.let { SimpleText(it.message) },
        canSubmit = canSubmit) }
    }

    fun onChangeFeeder(feeder:Panel)=intent{
        reduce { state.copy(feeder=feeder) }
    }


    fun onSubmit()=intent{
         addPanel(state.feeder!!,state.code,state.description,state.length be  UnitOfLength.M)
        navigationManager.back()
    }




}