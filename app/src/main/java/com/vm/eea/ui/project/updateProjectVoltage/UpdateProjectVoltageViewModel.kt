package com.vm.eea.ui.project.updateProjectVoltage

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.UnitOfVoltage
import com.vm.eea.domain.Voltage
import com.vm.eea.domain.defaultVoltage.GetDefaultVoltages
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectVoltage
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.IText
import com.vm.eea.utilities.Validator.Companion.validate
import com.vm.eea.utilities.format
import com.vm.eea.utilities.positiveNumber
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


class UpdateProjectVoltageViewModel(
    private val projectId: Long,
    private val system: PowerSystem,
    private val getProject: GetProject,
    private val navigationManager: NavigationManager,
    private val updateProjectVoltage: UpdateProjectVoltage,
    private val getDefaultVoltages: GetDefaultVoltages
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing> =
        container(UiState.init(system)){
            intent {
                val projectFlow=getProject(projectId)
                val defaultsFlow=getDefaultVoltages(system)
               projectFlow.combine(defaultsFlow){project,defaults->
                   val value=when(system){
                       PowerSystem.SinglePhase -> project.singlePhaseVoltage
                       PowerSystem.ThreePhase -> project.threePhaseVoltage
                       PowerSystem.TwoPhase -> TODO()
                   }
                 value to  defaults.map {
                     SelectableItem(it.voltage,value==it.voltage)
                   }
                }.collect {
                   reduce { state.copy(defaults = it.second,
                       value = it.first.value.format(),
                       unit = it.first.unit,canExecute = true) }
               }

            }


         }

    fun onChange(value:String,unit:UnitOfVoltage)=intent {

        val validationError= validate.positiveNumber(value,"")
        reduce { state.copy(value=value,unit=unit,
            error = validationError?.let { IText.simple(it.message) },
            canExecute = validationError==null ) }

    }



    fun onDefaultSelect(value:Voltage)=intent{

        updateProjectVoltage(projectId, value,system,false)
        navigationManager.back()
    }

    fun submit(addToDefaults:Boolean)=intent{
        updateProjectVoltage(projectId, Voltage(state.value.toDouble(),state.unit),system,addToDefaults)
       // reduce { state.copy(powerfactor = "") }
        navigationManager.back()
    }
}