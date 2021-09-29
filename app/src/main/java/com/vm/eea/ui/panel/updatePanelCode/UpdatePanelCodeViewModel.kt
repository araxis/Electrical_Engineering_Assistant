package com.vm.eea.ui.panel.updatePanelCode

import androidx.lifecycle.ViewModel
import com.vm.eea.application.PanelId
import com.vm.eea.application.panel.IGetPanelCode
import com.vm.eea.application.panel.update.UpdatePanelCode
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.SimpleText
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.notNullOrBlank
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelCodeViewModel(
    private val panelId: PanelId,
    private val getPanel: IGetPanelCode,
    private val updatePanelCode: UpdatePanelCode,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing> = container(com.vm.eea.ui.project.updateProjectCode.UiState.init()){
            onIO {
               val codeInfo= getPanel(panelId)
                    intent {
                        reduce { state.copy(code = codeInfo.code,description = codeInfo.description,canSubmit = true) }
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
        reduce {state.copy(description = value)}
    }

    fun onSubmit()=intent{
        updatePanelCode(panelId,state.code,state.description)
        navigationManager.back()
    }
}