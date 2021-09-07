package com.vm.eea.ui.project.updateProjectSoilThermalResistivity

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.ThermalResistivity
import com.vm.eea.domain.UnitOfThermalResistivity
import com.vm.eea.domain.defaultSiolResistivity.GetDefaultSoilResistivity
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectSoilResistivity
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.IText
import com.vm.eea.utilities.Validator.Companion.validate
import com.vm.eea.domain.format
import com.vm.eea.utilities.positiveNumber
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


class UpdateProjectSoilThermalResistivityViewModel(
    private val projectId: Long,
    private val getProject: GetProject,
    private val navigationManager: NavigationManager,
    private val updateProjectSoilResistivity: UpdateProjectSoilResistivity,
    private val getDefaultSoilResistivity: GetDefaultSoilResistivity
):ContainerHost<UpdateProjectSoilThermalResistivityState,Nothing> ,ViewModel() {
    override val container: Container<UpdateProjectSoilThermalResistivityState, Nothing> =
        container(UpdateProjectSoilThermalResistivityState.init()){
            intent {
                val projectFlow=getProject(projectId)
                val defaultsFlow=getDefaultSoilResistivity()
               projectFlow.combine(defaultsFlow){project,defaults->
                 project.soilResistivity to  defaults.map {
                       SelectableItem(it.value,project.soilResistivity==it.value)
                   }
                }.collect {
                  reduce { state.copy(defaults = it.second,
                      value = it.first.value.format(),
                       unit=it.first.unit,
                        canExecute = true) }
               }

            }


         }

    fun onChange(value:String, unitOfThermalResistivity: UnitOfThermalResistivity)=intent {

        val validationError= validate.positiveNumber(value,"")
        reduce { state.copy(value=value,unit=unitOfThermalResistivity,
            error = validationError?.let { IText.simple(it.message) },
            canExecute = validationError==null ) }

    }



    fun onDefaultSelect(value: ThermalResistivity)=intent{

        updateProjectSoilResistivity(projectId, value,false)
        navigationManager.back()
    }

    fun submit(addToDefaults:Boolean)=intent{
        updateProjectSoilResistivity(projectId, ThermalResistivity(state.value.toDouble(),state.unit),addToDefaults)
        reduce { state.copy(value = "") }
        navigationManager.back()
    }
}