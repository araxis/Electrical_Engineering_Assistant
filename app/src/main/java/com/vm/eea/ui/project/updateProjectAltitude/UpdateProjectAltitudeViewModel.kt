package com.vm.eea.ui.project.updateProjectAltitude

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Length
import com.vm.eea.application.format
import com.vm.eea.application.project.IGetProjectAltitude
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.update.UpdateProjectAltitude
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


class UpdateProjectAltitudeViewModel(
    private val projectId: ProjectId,
    private val getProjectAltitude: IGetProjectAltitude,
    private val navigationManager: NavigationManager,
    private val updateProjectAltitude: UpdateProjectAltitude,
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing> =
        container(UiState.init()){
            onIO {
                val altitude=getProjectAltitude(projectId)
                intent {
                reduce {
                    state.copy(value = altitude.value.format(),unit = altitude.unit)
                }

                }
            }



         }

    fun onChange(value:String,unit: Length.Unit)=intent {

        val validationError= validate.positiveNumber(value,"")
        reduce { state.copy(value=value,unit=unit,
            error = validationError?.let { IText.simple(it.message) },
            canExecute = validationError==null ) }

    }





    fun submit(addToDefaults:Boolean)=intent{
        updateProjectAltitude(projectId, Length(state.value.toDouble(),state.unit))
       // reduce { state.copy(demandFactor = "") }
        navigationManager.back()
    }
}