package com.vm.eea.ui.project.updateProjectTemperature

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Temperature
import com.vm.eea.application.UnitOfTemperature
import com.vm.eea.application.Environment
import com.vm.eea.application.format
import com.vm.eea.application.project.IGetProjectTemperature
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.update.UpdateProjectTemperature
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


class UpdateProjectTemperatureViewModel(
    private val projectId: ProjectId,
    private val environment: Environment,
    private val getProject: IGetProjectTemperature,
    private val navigationManager: NavigationManager,
    private val updater: UpdateProjectTemperature
):ContainerHost<UpdateProjectTemperatureState,Nothing> ,ViewModel() {
    override val container: Container<UpdateProjectTemperatureState, Nothing> =
        container(UpdateProjectTemperatureState.init(environment)){
            onIO {
                val temperature=getProject(projectId,environment)
                intent {
                        reduce { state.copy(value = temperature.value.format(),unit = temperature.unit) }

                }
            }



         }

    fun onChange(value:String,unit: UnitOfTemperature)=intent {

        val validationError= validate.positiveNumber(value,"")
        reduce { state.copy(value=value,unit=unit,
            error = validationError?.let { IText.simple(it.message) },
            canExecute = validationError==null ) }

    }




    fun submit(addToDefaults:Boolean)=intent{
        updater(projectId, Temperature(state.value.toDouble(),state.unit),environment)
       // reduce { state.copy(demandFactor = "") }
        navigationManager.back()
    }
}