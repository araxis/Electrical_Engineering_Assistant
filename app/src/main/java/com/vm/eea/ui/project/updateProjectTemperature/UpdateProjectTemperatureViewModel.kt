package com.vm.eea.ui.project.updateProjectTemperature

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.Environment
import com.vm.eea.domain.Temperature
import com.vm.eea.domain.UnitOfTemperature
import com.vm.eea.domain.defaultGroundTemperature.GetDefaultTemperatures
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectTemperature
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


class UpdateProjectTemperatureViewModel(
    private val projectId: Long,
    private val environment: Environment,
    private val getProject: GetProject,
    private val navigationManager: NavigationManager,
    private val updateProjectTemperature: UpdateProjectTemperature,
    private val getDefaultTemperatures: GetDefaultTemperatures
):ContainerHost<UpdateProjectTemperatureState,Nothing> ,ViewModel() {
    override val container: Container<UpdateProjectTemperatureState, Nothing> =
        container(UpdateProjectTemperatureState.init(environment)){
            intent {
                val projectFlow=getProject(projectId)
                val defaultsFlow=getDefaultTemperatures(environment)
               projectFlow.combine(defaultsFlow){project,defaults->
                   val value=when(environment){
                       Environment.Ambient -> project.ambientTemperature
                       Environment.Ground -> project.groundTemperature
                   }
                 value to  defaults.map {
                       SelectableItem(it.value,value==it.value)
                   }
                }.collect {
                  reduce { state.copy(defaults = it.second,value = it.first.value.format(),canExecute = true) }
               }

            }


         }

    fun onChange(value:String,unit:UnitOfTemperature)=intent {

        val validationError= validate.positiveNumber(value,"")
        reduce { state.copy(value=value,unit=unit,
            error = validationError?.let { IText.simple(it.message) },
            canExecute = validationError==null ) }

    }



    fun onDefaultSelect(value: Temperature)=intent{

        updateProjectTemperature(projectId, value,environment,false)
        navigationManager.back()
    }

    fun submit(addToDefaults:Boolean)=intent{
        updateProjectTemperature(projectId, Temperature(state.value.toDouble(),state.unit),environment,addToDefaults)
       // reduce { state.copy(powerfactor = "") }
        navigationManager.back()
    }
}