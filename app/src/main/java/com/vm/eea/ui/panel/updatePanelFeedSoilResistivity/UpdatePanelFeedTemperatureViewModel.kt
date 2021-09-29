package com.vm.eea.ui.panel.updatePanelFeedSoilResistivity

import androidx.lifecycle.ViewModel
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.ThermalResistivity
import com.vm.eea.application.panelToPanelRelation.IGetPanelFeedThermalResistivity
import com.vm.eea.application.panelToPanelRelation.UpdatePanelFeedSoilThermalResistivity
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelFeedSoilResistivityViewModel(
    private val relationId: RelationId,
    private val updatePanelFeed: UpdatePanelFeedSoilThermalResistivity,
    private val getInfo: IGetPanelFeedThermalResistivity,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState()){
             onIO {
                 val items=getInfo(relationId)
                 intent { reduce { state.copy(items = items) }}
             }
    }



    fun onItemSelect(item: SelectableItem<ThermalResistivity>)=intent{
        updatePanelFeed(relationId,item.value)
        navigationManager.back()
    }


}