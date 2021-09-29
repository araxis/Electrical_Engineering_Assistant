package com.vm.eea.ui.panel.updatePanelFeedConductor

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Conductor
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToPanelRelation.IGetPanelFeedConductor
import com.vm.eea.application.panelToPanelRelation.UpdatePanelFeedConductor
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelFeedConductorViewModel(
    private val relationId: RelationId,
    private val getFeedConductor: IGetPanelFeedConductor,
    private val updatePanelFeed: UpdatePanelFeedConductor,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
        = container(UiState.init()){
                onIO {
                    val items=getFeedConductor(relationId)
                    intent {reduce { state.copy(items = items) } }
                }
    }

    fun onItemSelect(item: SelectableItem<Conductor>)=onIO {
        updatePanelFeed(relationId,item.value)
        navigationManager.back()
    }
}