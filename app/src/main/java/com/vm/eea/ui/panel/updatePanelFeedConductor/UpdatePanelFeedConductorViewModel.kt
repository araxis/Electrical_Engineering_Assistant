package com.vm.eea.ui.panel.updatePanelFeedConductor

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.Conductor
import com.vm.eea.domain.panelToPanelRelation.GetFeedingRelationByRelation
import com.vm.eea.domain.panelToPanelRelation.UpdatePanelFeed
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

class UpdatePanelFeedConductorViewModel(
    private val relationId: Long,
    private val getFeedingRelationByRelation: GetFeedingRelationByRelation,
    private val updatePanelFeed: UpdatePanelFeed,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
        = container(UiState.init()){
            intent {
                getFeedingRelationByRelation(relationId)
                    .map { relation->
                        Conductor.values().map { SelectableItem(it,it==relation.conductor) }
                    }.collect {
                        reduce { state.copy(items = it) }
                    }
            }
    }

    fun onItemSelect(item:Conductor)=onIO {
        updatePanelFeed(relationId,item)
        navigationManager.back()
    }
}