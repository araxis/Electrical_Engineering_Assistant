package com.vm.eea.ui.panel.updatePanelFeedInsulation

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Insulation
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToPanelRelation.IGetPanelFeedInsulation
import com.vm.eea.application.panelToPanelRelation.UpdatePanelFeedInsulation
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelFeedInsulationViewModel(
    private val relationId: RelationId,
    private val getInfo: IGetPanelFeedInsulation,
    private val updatePanelFeed: UpdatePanelFeedInsulation,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
        = container(UiState.int()){
        onIO {
            val items=getInfo(relationId)
            intent {reduce { state.copy(items = items) }}
        }

    }

    fun onItemSelect(item: SelectableItem<Insulation>)=onIO {
        updatePanelFeed(relationId,item.value)
        navigationManager.back()
    }
}