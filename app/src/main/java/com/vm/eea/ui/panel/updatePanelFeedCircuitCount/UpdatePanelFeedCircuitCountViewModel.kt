package com.vm.eea.ui.panel.updatePanelFeedCircuitCount

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.CircuitCount
import com.vm.eea.domain.GetDefaultCircuitCounts
import com.vm.eea.domain.panelToPanelRelation.GetFeedingRelationByRelation
import com.vm.eea.domain.panelToPanelRelation.UpdatePanelFeed
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

class UpdatePanelFeedCircuitCountViewModel(
    private val relationId: Long,
    private val getDefaultCircuitCounts: GetDefaultCircuitCounts,
    private val getFeedingRelationByRelation: GetFeedingRelationByRelation,
    private val updatePanelFeed: UpdatePanelFeed,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
        = container(UiState.init()){
            intent {
              getFeedingRelationByRelation(relationId).combine( getDefaultCircuitCounts()){relation,defaults->
                  defaults.map { SelectableItem(it,it==relation.circuitCount) }
              }.collect {
                  reduce { state.copy(items = it) }
              }

            }
    }

    fun onItemSelect(item:CircuitCount)=onIO{
        updatePanelFeed(relationId,item)
        navigationManager.back()
    }
}