package com.vm.eea.ui.panel.updatePanelFeedMethodOfInstallation

import androidx.lifecycle.ViewModel
import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToPanelRelation.IGetPanelFeedMethodOfInstallation
import com.vm.eea.application.panelToPanelRelation.UpdatePanelFeedMethodOfInstallation
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelFeedMethodOfInstallationViewModel(
    private val relationId: RelationId,
    private val updatePanelFeed: UpdatePanelFeedMethodOfInstallation,
    private val getInfo: IGetPanelFeedMethodOfInstallation,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState(emptyList())){
        onIO {
               val items=  getInfo(relationId)
                intent {reduce { state.copy(defaults = items) } }}
        }

     fun onItemSelect(item: SelectableItem<MethodOfInstallation>)=onIO {
         updatePanelFeed(relationId,item.value)
         navigationManager.back()
     }

}