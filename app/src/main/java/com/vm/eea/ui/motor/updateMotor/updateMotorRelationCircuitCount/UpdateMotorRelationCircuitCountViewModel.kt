package com.vm.eea.ui.motor.updateMotor.updateMotorRelationCircuitCount

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.CircuitCount
import com.vm.eea.domain.RelationId
import com.vm.eea.domain.panelToMotorRelation.UpdateMotorRelationCircuitCount
import com.vm.eea.ui.GetDefaultCircuitCounts
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

class UpdateMotorRelationCircuitCountViewModel(
    private val relationId: RelationId,
    private val getDefaultCircuitCounts: GetDefaultCircuitCounts,
    private val getMotorRelationCircuitCount: GetMotorRelationCircuitCount,
    private val updateMotorRelationCircuitCount: UpdateMotorRelationCircuitCount,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState()){
            intent {
                getMotorRelationCircuitCount(relationId).combine( getDefaultCircuitCounts()){value,defaults->
                  defaults.map { SelectableItem(it,it==value) }
              }.collect {
                  reduce { state.copy(items = it) }
              }

            }
    }

    fun onItemSelect(item: CircuitCount)=onIO{
        updateMotorRelationCircuitCount(relationId,item)
        navigationManager.back()
    }
}