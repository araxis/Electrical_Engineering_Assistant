package com.vm.eea.ui.project.updateProjectInsulation

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.Insulation
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectInsulation
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

class UpdateProjectInsulationViewModel(
    private val projectId: Long,
    private val getProject: GetProject,
    private val updateProjectInsulation: UpdateProjectInsulation,
    private val navigationManager: NavigationManager
):ContainerHost<UpdateProjectInsulationState,Nothing>,ViewModel() {
    override val container: Container<UpdateProjectInsulationState, Nothing>
         = container(UpdateProjectInsulationState(emptyList())){
        onIO {
            getProject(projectId).map {
                Insulation.values().map { o-> SelectableItem(o,o==it.insulation) }
            }.collect {
                intent {
                    reduce { state.copy(defaults = it) } }
                }
            }
        }

     fun onItemSelect(item: Insulation)=onIO {
         updateProjectInsulation(projectId,item)
         navigationManager.back()
     }

}