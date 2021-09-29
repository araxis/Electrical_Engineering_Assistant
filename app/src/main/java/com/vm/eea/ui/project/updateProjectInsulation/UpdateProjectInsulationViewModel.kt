package com.vm.eea.ui.project.updateProjectInsulation

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Insulation
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.project.IGetProjectInsulation
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.update.UpdateProjectInsulation
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateProjectInsulationViewModel(
    private val projectId: ProjectId,
    private val getProject: IGetProjectInsulation,
    private val updater: UpdateProjectInsulation,
    private val navigationManager: NavigationManager
):ContainerHost<UpdateProjectInsulationState,Nothing>,ViewModel() {
    override val container: Container<UpdateProjectInsulationState, Nothing>
         = container(UpdateProjectInsulationState(emptyList())) {
        onIO {
            val result = getProject(projectId)
            intent { reduce { state.copy(defaults = result) } }
        }
    }

     fun onItemSelect(item: SelectableItem<Insulation>)=onIO {
         if(item.isSelected) return@onIO
         updater(projectId,item.value)
         navigationManager.back()
     }

}