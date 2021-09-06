package com.vm.eea.ui.project.addProject

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.project.AddSimpleProject
import com.vm.eea.domain.project.SimpleNewProject
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.SimpleText
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.notNullOrBlank
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddProjectViewModel(private val addSimpleProject: AddSimpleProject,
                          private val navigationManager: NavigationManager):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init())


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
        addSimpleProject(SimpleNewProject(state.code, state.description))
        navigationManager.back()
    }
}