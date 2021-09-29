package com.vm.eea.ui.motor.updateMotor.updateMotorRelationConductor

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Conductor
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToMotorRelation.UpdateMotorFeedConductor
import com.vm.eea.data.panelToMotorRelation.GetMotorRelationConductor
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorRelationConductorViewModel(
    private val relationId: RelationId,
    private val getMotorRelationConductor: GetMotorRelationConductor,
    private val updatePanelToMotorFeed: UpdateMotorFeedConductor,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
       = container(UiState()){
            onIO {
               val items=getMotorRelationConductor(relationId)
                intent {reduce { state.copy(items = items) }}
            }

        }

    fun onValueChange(value: SelectableItem<Conductor>)=intent {
        updatePanelToMotorFeed(relationId,value.value)
        navigationManager.back()
    }


}