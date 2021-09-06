package com.vm.eea.ui.panel.updateFeeder

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.panel.GetAvailableFeeders
import com.vm.eea.domain.panel.GetPanelFeeder
import com.vm.eea.domain.panel.UpdatePanelFeeder
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateFeederViewModel(
    private val panelId: Long,
    private val getAvailableFeeders: GetAvailableFeeders,
    private val getPanelFeeder: GetPanelFeeder,
    private val updatePanelFeeder: UpdatePanelFeeder,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init()){
            intent {
                getPanelFeeder(panelId).combine(getAvailableFeeders(panelId)){feeder,panels->
                   panels.map { FeederItem(it,it.id==feeder.id) }
                }.collect {
                    reduce { state.copy(feeders = it) }
                }
            }
    }

    fun onSelect(item:FeederItem)=onIO{
        updatePanelFeeder(panelId,item.feeder)
        navigationManager.back()
    }
}