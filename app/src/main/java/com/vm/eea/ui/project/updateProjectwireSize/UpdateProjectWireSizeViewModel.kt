package com.vm.eea.ui.project.updateProjectwireSize

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.WireSize
import com.vm.eea.domain.defaultWireSize.GetDefaultWireSizes
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectWireSize
import com.vm.eea.domain.project.WireSizeType
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.SelectableItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateProjectWireSizeViewModel(
    private val projectId: Long,
    private val sizingType: WireSizeType,
    private val getDefaultWireSizes: GetDefaultWireSizes,
    private val getProject: GetProject,
    private val updateProjectWireSize: UpdateProjectWireSize,
    private val navigationManager: NavigationManager
):ContainerHost<UpdateProjectWireSizeState,Nothing>,ViewModel() {
    override val container: Container<UpdateProjectWireSizeState, Nothing>
         = container(UpdateProjectWireSizeState.init(sizingType)){
        intent {
            getProject(projectId).flatMapMerge {
                getDefaultWireSizes(it.unitOfWireSize)
                    .map { defaults->
                        when(sizingType){
                            WireSizeType.Max -> {
                                defaults
                                    .sortedByDescending {o->o.wireSize.value  }
                                    .map { o-> SelectableItem(o.wireSize,o.wireSize==it.maxWireSize)}
                            }
                            WireSizeType.Min ->{
                                defaults
                                    .sortedBy {o->o.wireSize.value  }
                                    .map { o-> SelectableItem(o.wireSize,o.wireSize==it.minWireSize)}
                            }
                        }



                    }

            }.collect {
                reduce { state.copy(defaults = it.sortedByDescending {o-> o.isSelected }) }
            }
        }

        }
    fun onDefaultItemSelect(item:WireSize)=intent{
        updateProjectWireSize(projectId,item,sizingType)
        navigationManager.back()
    }
}