package com.vm.eea.ui.motor.updateMotor.updateMotorRelationSoilResistivity

import androidx.lifecycle.ViewModel
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.ThermalResistivity
import com.vm.eea.application.panelToMotorRelation.IGetMotorFeedThermalResistivity
import com.vm.eea.application.panelToMotorRelation.UpdateMotorFeedSoilThermalResistivity
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorRelationThermalResistivityViewModel(
    private val relationId: RelationId,
    private val getMotorRelationThermalResistivity: IGetMotorFeedThermalResistivity,
    private val updatePanelToMotorFeed: UpdateMotorFeedSoilThermalResistivity,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState()){
        onIO {
            val items=getMotorRelationThermalResistivity(relationId)
            intent { reduce { state.copy(items=items) } }
        }
    }
    fun onItemSelect(item: SelectableItem<ThermalResistivity>) =onIO{
        updatePanelToMotorFeed(relationId,item.value)
        navigationManager.back()
    }
}