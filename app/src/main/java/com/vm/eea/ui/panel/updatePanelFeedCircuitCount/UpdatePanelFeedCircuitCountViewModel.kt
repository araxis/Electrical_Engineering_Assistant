package com.vm.eea.ui.panel.updatePanelFeedCircuitCount

import androidx.lifecycle.ViewModel
import com.vm.eea.application.CircuitCount
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToPanelRelation.IGetPanelRelationCircuitCount
import com.vm.eea.application.panelToPanelRelation.UpdatePanelFeedCircuitCount
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelFeedCircuitCountViewModel(
    private val relationId: RelationId,
    private val getPanelRelationCircuitCount: IGetPanelRelationCircuitCount,
    private val updatePanelFeed: UpdatePanelFeedCircuitCount,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState.init()){
                onIO {
                   val result= getPanelRelationCircuitCount(relationId)
                    intent {
                        reduce { state.copy(items = result) }
                    }
                }

    }

    fun onItemSelect(item: SelectableItem<CircuitCount>)=onIO{
        updatePanelFeed(relationId,item.value)
        navigationManager.back()
    }
}