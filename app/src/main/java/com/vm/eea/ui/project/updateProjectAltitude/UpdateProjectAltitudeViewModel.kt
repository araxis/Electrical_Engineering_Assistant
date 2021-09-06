package com.vm.eea.ui.project.updateProjectAltitude

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.Length
import com.vm.eea.domain.UnitOfLength
import com.vm.eea.domain.defaultAltitude.GetDefaultAltitudes
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectAltitude
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


class UpdateProjectAltitudeViewModel(
    private val projectId: Long,
    private val getProject: GetProject,
    private val navigationManager: NavigationManager,
    private val updateProjectAltitude: UpdateProjectAltitude,
    private val getDefaultAltitudes: GetDefaultAltitudes
):ContainerHost<UpdateProjectAltitudeState,Nothing> ,ViewModel() {
    override val container: Container<UpdateProjectAltitudeState, Nothing> =
        container(UpdateProjectAltitudeState.init()){
            intent {
                val projectFlow=getProject(projectId)
                val defaultsFlow=getDefaultAltitudes()
               projectFlow.combine(defaultsFlow){project,defaults->
                 project.altitude to  defaults.map {
                     SelectableItem(it.value,project.altitude==it.value)
                   }
                }.collect {
                   reduce { state.copy(defaults = it.second,
                       value = it.first.value.format(),
                       unit = it.first.unit,canExecute = true) }
               }

            }


         }

    fun onChange(value:String,unit:UnitOfLength)=intent {

        val validationError= validate.positiveNumber(value,"")
        reduce { state.copy(value=value,unit=unit,
            error = validationError?.let { IText.simple(it.message) },
            canExecute = validationError==null ) }

    }



    fun onDefaultSelect(value:Length)=intent{

        updateProjectAltitude(projectId, value,false)
        navigationManager.back()
    }

    fun submit(addToDefaults:Boolean)=intent{
        updateProjectAltitude(projectId, Length(state.value.toDouble(),state.unit),addToDefaults)
       // reduce { state.copy(powerfactor = "") }
        navigationManager.back()
    }
}