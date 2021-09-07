package com.vm.eea.ui.project.updateProjectPowerfactor

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.PowerFactor
import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.defaultPowerfactor.GetDefaultPowerFactors
import com.vm.eea.domain.format
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectPowerfactor
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateProjectPowerfactorViewModel(
    private val projectId: Long,
    private val system: PowerSystem,
    private val getProject: GetProject,
    private val getDefaultPowerFactors: GetDefaultPowerFactors,
    private val updateProjectPowerfactor: UpdateProjectPowerfactor,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init(system)){
            val projectFlow=getProject(projectId)
            val defaultsFlow=getDefaultPowerFactors(system)
            val flow=projectFlow.combine(defaultsFlow){project,defaults->
                val currentValue=when(system){
                    PowerSystem.SinglePhase -> project.singlePhasePowerFactor
                    PowerSystem.ThreePhase -> project.threePhasePowerFactor
                    PowerSystem.TwoPhase -> TODO()
                }
              currentValue to  defaults.map { SelectableItem(it.value,it.value==currentValue) }
            }
             intent {
                 flow.collect {
                 reduce {
                     state.copy(defaults = it.second,value = it.first.value.format(),canSubmit = true) }
                      }
             }
    }

    fun onChange(value:String)=intent {
        val validationResult=Validator.validate.inRange(value,.1,1.0,"")
        reduce { state.copy(value=value,
            error = validationResult?.let { SimpleText(it.message) },
            canSubmit = validationResult==null) }
    }
    fun onSubmit(addToDefaults:Boolean)=intent{
        val pf= PowerFactor(state.value.toDouble())
        updateProjectPowerfactor(projectId,pf,system,addToDefaults)
        navigationManager.back()
    }
    fun onDefaultItemSelect(item: PowerFactor)= onIO {
        updateProjectPowerfactor(projectId,item,system,false)
        navigationManager.back()
        }


}