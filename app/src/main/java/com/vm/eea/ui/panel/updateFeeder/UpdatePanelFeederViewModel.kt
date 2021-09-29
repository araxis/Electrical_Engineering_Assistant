package com.vm.eea.ui.panel.updateFeeder

import androidx.lifecycle.ViewModel
import com.vm.eea.application.PanelId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panel.IGetAvailablePanelFeeders
import com.vm.eea.application.panel.SimplePanel
import com.vm.eea.application.panel.update.UpdatePanelFeeder
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelFeederViewModel(
    private val panelId: PanelId,
    private val getAvailableFeeders: IGetAvailablePanelFeeders,
    private val updatePanelFeeder: UpdatePanelFeeder,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init()){
              onIO {
                  val feeders= getAvailableFeeders(panelId)
                  intent {reduce { state.copy(feeders = feeders.map { SelectableItem(it,false) }) }}
              }

    }

    fun onSelect(item:SelectableItem<SimplePanel>)=onIO{
        updatePanelFeeder(panelId,item.value.id)
        navigationManager.back()
    }
}