package com.vm.eea.ui.project.updateProjectVoltage

import androidx.lifecycle.ViewModel
import com.vm.eea.application.PowerSystem
import com.vm.eea.application.Voltage
import com.vm.eea.application.be
import com.vm.eea.application.format
import com.vm.eea.application.project.IGetProjectVoltage
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.update.UpdateProjectVoltage
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.IText
import com.vm.eea.utilities.Validator.Companion.validate
import com.vm.eea.utilities.onIO
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


class UpdateProjectVoltageViewModel(
    private val projectId: ProjectId,
    private val system: PowerSystem,
    private val getProject: IGetProjectVoltage,
    private val navigationManager: NavigationManager,
    private val updateProject: UpdateProjectVoltage
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing> =
        container(UiState.init(system)){
            onIO {
                val voltage=getProject(projectId,system )
                intent {
                    reduce { state.copy(value = voltage.value.format(),unit = voltage.unit) }
                }
            }
         }

    fun onChange(value:String,unit: Voltage.Unit)=intent {

        val validationError= validate.positiveNumber(value,"")
        reduce { state.copy(value=value,unit=unit,
            error = validationError?.let { IText.simple(it.message) },
            canExecute = validationError==null ) }

    }


    fun submit(addToDefaults:Boolean)=intent{
        updateProject(projectId, state.value be state.unit,system)
       // reduce { state.copy(demandFactor = "") }
        navigationManager.back()
    }
}