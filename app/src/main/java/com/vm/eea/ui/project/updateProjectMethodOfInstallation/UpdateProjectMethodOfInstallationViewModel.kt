package com.vm.eea.ui.project.updateProjectMethodOfInstallation

import androidx.lifecycle.ViewModel
import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.project.IGetProjectMethodOfInstallation
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.update.UpdateProjectMethodOfInstallation
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateProjectMethodOfInstallationViewModel(
    private val projectId: ProjectId,
    private val getInfo: IGetProjectMethodOfInstallation,
    private val updater: UpdateProjectMethodOfInstallation,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState(emptyList())){
        onIO {
            val result=getInfo(projectId)
            intent {
                reduce { state.copy(defaults = result) }
            }
        }
         }

     fun onItemSelect(item: SelectableItem<MethodOfInstallation>)=onIO {
         updater(projectId,item.value)
         navigationManager.back()
     }

}