package com.vm.eea.ui.project.updateProjectUnitOfPower

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.UnitOfPower
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectUnitOfPower
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

class UpdateProjectUnitOfPowerViewModel(
    private val projectId: Long,
    private val getProject: GetProject,
    private val updateProjectUnitOfPower: UpdateProjectUnitOfPower,
    private val navigationManager: NavigationManager
):ContainerHost<UpdateProjectUnitOfPowerState,Nothing>,ViewModel() {
    override val container: Container<UpdateProjectUnitOfPowerState, Nothing>
         = container(UpdateProjectUnitOfPowerState(emptyList())){
        onIO {
            getProject(projectId).map {
                UnitOfPower.values().map {o-> SelectableItem(o,o==it.unitOfPower) }
            }.collect {
                intent {
                    reduce { state.copy(defaults = it) } }
                }
            }
        }

     fun onItemSelect(item:UnitOfPower)=onIO {
         updateProjectUnitOfPower(projectId,item)
         navigationManager.back()
     }

}