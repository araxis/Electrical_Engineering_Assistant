package com.vm.eea.ui.project.updateProjectUnitOfWireSize

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.UnitOfWireSize
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectUnitOfWireSize
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

class UpdateProjectUnitOfWireSizeViewModel(
    private val projectId: Long,
    private val getProject: GetProject,
    private val updateProjectUnitOfWireSize: UpdateProjectUnitOfWireSize,
    private val navigationManager: NavigationManager
):ContainerHost<UpdateProjectUnitOfWireSizeState,Nothing>,ViewModel() {
    override val container: Container<UpdateProjectUnitOfWireSizeState, Nothing>
         = container(UpdateProjectUnitOfWireSizeState(emptyList())){
        onIO {
            getProject(projectId).map {
                UnitOfWireSize.values().map { o-> SelectableItem(o,o==it.unitOfWireSize) }
            }.collect {
                intent {
                    reduce { state.copy(defaults = it) } }
                }
            }
        }

     fun onItemSelect(item: UnitOfWireSize)=onIO {
         updateProjectUnitOfWireSize(projectId,item)
         navigationManager.back()
     }

}