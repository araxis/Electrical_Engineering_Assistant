package com.vm.eea.ui.project.updateProjectwireSize

import androidx.lifecycle.ViewModel
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.WireSize
import com.vm.eea.application.WireSizeType
import com.vm.eea.application.project.IGetProjectWireSize
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.update.UpdateProjectWireSize
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateProjectWireSizeViewModel(
    private val projectId: ProjectId,
    private val sizingType: WireSizeType,
    private val getInfo: IGetProjectWireSize,
    private val updateProject: UpdateProjectWireSize,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init(sizingType)){
            onIO {
                val items=getInfo(projectId,sizingType)
                intent { reduce { state.copy(defaults = items) } }
            }

        }
    fun onDefaultItemSelect(item: SelectableItem<WireSize>)=intent{
        updateProject(projectId,item.value,sizingType)
        navigationManager.back()
    }
}