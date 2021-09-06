package com.vm.eea.ui.project.updateProjectUnitOfTemperature

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.UnitOfTemperature
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectUnitOfTemperature
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.onIO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateProjectUnitOfTemperatureViewModel(
    private val projectId: Long,
    private val getProject: GetProject,
    private val updateProjectUnitOfTemperature: UpdateProjectUnitOfTemperature,
    private val navigationManager: NavigationManager
):ContainerHost<UpdateProjectUnitOfTemperatureState,Nothing>,ViewModel() {
    override val container: Container<UpdateProjectUnitOfTemperatureState, Nothing>
         = container(UpdateProjectUnitOfTemperatureState(emptyList())){
        onIO {
            getProject(projectId).map {
                UnitOfTemperature.values().map { o-> SelectableItem(o,o==it.unitOfTemperature) }
            }.collect {
                intent {
                    reduce { state.copy(defaults = it) } }
                }
            }
        }

     fun onItemSelect(item: UnitOfTemperature)=onIO {
         updateProjectUnitOfTemperature(projectId,item)
         navigationManager.back()
     }

}