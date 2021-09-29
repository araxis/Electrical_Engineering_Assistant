package com.vm.eea.ui.project.updateProjectConductor

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Conductor
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.project.IGetProjectConductor
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.update.UpdateProjectConductor
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateProjectConductorViewModel(
    private val projectId: ProjectId,
    private val getProjectConductor: IGetProjectConductor,
    private val updater: UpdateProjectConductor,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing> = container(UiState(emptyList())) {
        onIO {
            val conductors = getProjectConductor(projectId)
            intent {
                reduce {
                    state.copy(defaults = conductors)
                }
            }
        }



    }
    fun onItemSelect(item: SelectableItem<Conductor>) = onIO {
        if(item.isSelected) return@onIO
        updater(projectId, item.value)
        navigationManager.back()
    }
}