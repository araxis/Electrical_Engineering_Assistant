package com.vm.eea.ui.project.updateProjectSoilThermalResistivity

import androidx.lifecycle.ViewModel
import com.vm.eea.application.ThermalResistivity
import com.vm.eea.application.UnitOfThermalResistivity
import com.vm.eea.application.format
import com.vm.eea.application.project.IGetProjectSoilThermalResistivity
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.update.UpdateProjectSoilThermalResistivity
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


class UpdateProjectSoilThermalResistivityViewModel(
    private val projectId: ProjectId,
    private val navigationManager: NavigationManager,
    private val getInfo:IGetProjectSoilThermalResistivity,
    private val updateProject: UpdateProjectSoilThermalResistivity,
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing> =
        container(UiState.init()){
            onIO {
                val result=getInfo(projectId)
                intent {
                    reduce { state.copy(value = result.value.format(),unit = result.unit) }
                }
            }



         }

    fun onChange(value:String, unitOfThermalResistivity: UnitOfThermalResistivity)=intent {

        val validationError= validate.positiveNumber(value,"")
        reduce { state.copy(value=value,unit=unitOfThermalResistivity,
            error = validationError?.let { IText.simple(it.message) },
            canExecute = validationError==null ) }

    }





    fun submit(addToDefaults:Boolean)=intent{
        updateProject(projectId, ThermalResistivity(state.value.toDouble(),state.unit))
        reduce { state.copy(value = "") }
        navigationManager.back()
    }
}