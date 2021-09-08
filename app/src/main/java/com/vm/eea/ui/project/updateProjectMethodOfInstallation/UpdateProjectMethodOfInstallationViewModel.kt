package com.vm.eea.ui.project.updateProjectMethodOfInstallation

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.MethodOfInstallation
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectMethodOfInstallation
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

class UpdateProjectMethodOfInstallationViewModel(
    private val projectId: Long,
    private val getProject: GetProject,
    private val updateProjectMethodOfInstallation: UpdateProjectMethodOfInstallation,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState(emptyList())){
        onIO {
            getProject(projectId).map {
                MethodOfInstallation.values().map { o-> SelectableItem(o,o==it.methodOfInstallation) }
            }.collect {
                intent {
                    reduce { state.copy(defaults = it) } }
                }
            }
        }

     fun onItemSelect(item: MethodOfInstallation)=onIO {
         updateProjectMethodOfInstallation(projectId,item)
         navigationManager.back()
     }

}