package com.vm.eea.ui.project.updateProjectUnitOfLength

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.UnitOfLength
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectUnitOfLength
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

class UpdateProjectUnitOfLengthViewModel(
    private val projectId: Long,
    private val getProject: GetProject,
    private val updateProjectUnitOfLength: UpdateProjectUnitOfLength,
    private val navigationManager: NavigationManager
):ContainerHost<UpdateProjectUnitOfLengthState,Nothing>,ViewModel() {
    override val container: Container<UpdateProjectUnitOfLengthState, Nothing>
         = container(UpdateProjectUnitOfLengthState(emptyList())){
        onIO {
            getProject(projectId).map {
                UnitOfLength.values().map { o-> SelectableItem(o,o==it.unitOfLength) }
            }.collect {
                intent {
                    reduce { state.copy(defaults = it) } }
                }
            }
        }

     fun onItemSelect(item: UnitOfLength)=onIO {
         updateProjectUnitOfLength(projectId,item)
         navigationManager.back()
     }

}