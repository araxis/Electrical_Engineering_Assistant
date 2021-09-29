package com.vm.eea.ui.motor.updateMotor.updateMotorRelationCircuitCount

import androidx.lifecycle.ViewModel
import com.vm.eea.application.CircuitCount
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToMotorRelation.IGetRelationCircuitCount
import com.vm.eea.application.panelToMotorRelation.UpdateFeedCircuitCount
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorRelationCircuitCountViewModel(
    private val relationId: RelationId,
    private val getMotorRelationCircuitCount: IGetRelationCircuitCount,
    private val updatePanelToMotorFeed: UpdateFeedCircuitCount,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState()){
          onIO {
              val value=getMotorRelationCircuitCount(relationId)
              intent {reduce { state.copy(items = value) }}
          }

    }

    fun onItemSelect(item: SelectableItem<CircuitCount>)=onIO{
        updatePanelToMotorFeed(relationId,item.value)
        navigationManager.back()
    }
}