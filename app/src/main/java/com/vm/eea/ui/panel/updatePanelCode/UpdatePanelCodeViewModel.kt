package com.vm.eea.ui.panel.updatePanelCode

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.panel.GetPanel
import com.vm.eea.domain.panel.UpdatePanelCode
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.SimpleText
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.notNullOrBlank
import com.vm.eea.utilities.onIO
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelCodeViewModel(
    private val panelId: Long,
    private val getPanel: GetPanel,
    private val updatePanelCode: UpdatePanelCode,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing> = container(com.vm.eea.ui.project.updateProjectCode.UiState.init()){
            onIO {
                getPanel(panelId).collect {
                    intent {
                        reduce { state.copy(code = it.code,description = it.description,canSubmit = true) }
                    }
                }
            }

    }

    fun onCodeChange(value:String)=intent{
        val validationResult= Validator.validate.notNullOrBlank(value,"")
        reduce {
            state.copy(code = value,
                codeError = validationResult?.let { SimpleText(it.message) },
                canSubmit = validationResult==null)
        }
    }

    fun onDescriptionChange(value:String)=intent{
        reduce {
            state.copy(description = value)
        }
    }

    fun onSubmit()=intent{
        updatePanelCode(panelId,state.code,state.description)
        navigationManager.back()
    }
}