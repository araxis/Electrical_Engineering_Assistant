package com.vm.eea.ui.project.updateProjectConductor

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.Conductor
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectConductor
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

class UpdateProjectConductorViewModel(
    private val projectId: Long,
    private val getProject: GetProject,
    private val updateProjectConductor: UpdateProjectConductor,
    private val navigationManager: NavigationManager
):ContainerHost<UpdateProjectConductorState,Nothing>,ViewModel() {
    override val container: Container<UpdateProjectConductorState, Nothing>
         = container(UpdateProjectConductorState(emptyList())){
        onIO {
            getProject(projectId).map {
                Conductor.values().map { o-> SelectableItem(o,o==it.conductor) }
            }.collect {
                intent {
                    reduce { state.copy(defaults = it) } }
                }
            }
        }

     fun onItemSelect(item: Conductor)=onIO {
         updateProjectConductor(projectId,item)
         navigationManager.back()
     }

}