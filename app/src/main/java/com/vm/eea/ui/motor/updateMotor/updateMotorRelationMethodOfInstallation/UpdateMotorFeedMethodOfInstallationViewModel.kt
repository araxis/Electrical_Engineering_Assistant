package com.vm.eea.ui.motor.updateMotor.updateMotorRelationMethodOfInstallation

import androidx.lifecycle.ViewModel
import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToMotorRelation.IGetMotorRelationMethodOfInstallation
import com.vm.eea.application.panelToMotorRelation.UpdateMotorFeedMethodOfInstallation
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorFeedMethodOfInstallationViewModel(
    private val relationId: RelationId,
    private val getMotorRelationMethodOfInstallation: IGetMotorRelationMethodOfInstallation,
    private val updatePanelToMotorFeed: UpdateMotorFeedMethodOfInstallation,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
       = container(UiState()){
            onIO {
                val items=getMotorRelationMethodOfInstallation(relationId)
                intent {reduce { state.copy(items = items) }}
            }
        }

    fun onValueChange(value: SelectableItem<MethodOfInstallation>)=intent {
        updatePanelToMotorFeed(relationId,value.value)
        navigationManager.back()
    }


}