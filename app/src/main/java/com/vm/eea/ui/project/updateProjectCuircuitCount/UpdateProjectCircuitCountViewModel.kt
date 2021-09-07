package com.vm.eea.ui.project.updateProjectCuircuitCount

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.CircuitCount
import com.vm.eea.domain.GetDefaultCircuitCounts
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectCircuitCount
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.onIO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateProjectCircuitCountViewModel(private val projectId:Long,
                                         private val getProject: GetProject,
                                         private val updateProjectCircuitCount: UpdateProjectCircuitCount,
                                         private val getDefaultCircuitCounts: GetDefaultCircuitCounts,
                                         private val navigationManager: NavigationManager):ContainerHost<UpdateProjectCircuitCountState,Nothing>,ViewModel() {
    override val container: Container<UpdateProjectCircuitCountState, Nothing>
         = container(UpdateProjectCircuitCountState(emptyList())){
            intent{
                val projectFlow=getProject(projectId)
                val defaultsFlow=getDefaultCircuitCounts()
                projectFlow.combine(defaultsFlow){project,defaults->
                    defaults.map { SelectableItem(it,it==project.circuitInTheSameConduit) }
                }.collect {
                    reduce { state.copy(defaults = it) }
                }
            }
    }



    fun onDefaultItemSelect(item: CircuitCount)=onIO{
        updateProjectCircuitCount(projectId,item)
        navigationManager.back()
    }
}