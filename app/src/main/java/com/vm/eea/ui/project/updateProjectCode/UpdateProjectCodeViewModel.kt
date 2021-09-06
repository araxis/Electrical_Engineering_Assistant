package com.vm.eea.ui.project.updateProjectCode

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectCode
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

class UpdateProjectCodeViewModel(
    private val projectId: Long,
    private val getProject: GetProject,
    private val updateProjectCode: UpdateProjectCode,
):ContainerHost<com.vm.eea.ui.panel.updatePanelCode.UiState,Nothing>,ViewModel() {
    override val container: Container<com.vm.eea.ui.panel.updatePanelCode.UiState, Nothing> = container(UiState.init()){
            onIO {
                getProject(projectId).collect {
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
        updateProjectCode(projectId,state.code,state.description)
    }
}