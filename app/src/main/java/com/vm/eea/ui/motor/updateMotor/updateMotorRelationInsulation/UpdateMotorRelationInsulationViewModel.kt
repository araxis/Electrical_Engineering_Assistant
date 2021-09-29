package com.vm.eea.ui.motor.updateMotor.updateMotorRelationInsulation

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Insulation
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToMotorRelation.IGetMotorFeedInsulation
import com.vm.eea.application.panelToMotorRelation.UpdateMotorFeedInsulation
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorRelationInsulationViewModel(
    private val relationId: RelationId,
    private val getMotorFeedInsulation: IGetMotorFeedInsulation,
    private val updatePanelToMotorFeed: UpdateMotorFeedInsulation,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
       = container(UiState()){
          onIO {
            val items= getMotorFeedInsulation(relationId)
              intent {  reduce { state.copy(items = items) } }
          }

        }

    fun onValueChange(value: SelectableItem<Insulation>)=intent {
        updatePanelToMotorFeed(relationId,value.value)
        navigationManager.back()
    }


}