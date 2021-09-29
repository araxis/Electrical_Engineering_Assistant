package com.vm.eea.ui.project.updateProjectCuircuitCount

import androidx.lifecycle.ViewModel
import com.vm.eea.application.CircuitCount
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.project.IGetProjectCircuitCount
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.update.UpdateProjectCircuitCount
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateProjectCircuitCountViewModel(private val projectId: ProjectId,
                                         private val getProject: IGetProjectCircuitCount,
                                         private val updater: UpdateProjectCircuitCount,
                                         private val navigationManager: NavigationManager):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState(emptyList())){
                onIO {
                    val items=getProject(projectId)
                    intent {
                        reduce { state.copy(defaults = items) }
                    }
                }
            intent{

            }
    }



    fun onDefaultItemSelect(item: SelectableItem<CircuitCount>)=onIO{
        if(item.isSelected) return@onIO
        updater(projectId,item.value)
        navigationManager.back()
    }
}