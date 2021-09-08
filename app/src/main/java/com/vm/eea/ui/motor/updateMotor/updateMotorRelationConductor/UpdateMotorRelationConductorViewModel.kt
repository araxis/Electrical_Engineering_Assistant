package com.vm.eea.ui.motor.updateMotor.updateMotorRelationConductor

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.Conductor
import com.vm.eea.domain.RelationId
import com.vm.eea.domain.panelToMotorRelation.UpdateMotorRelationConductor
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.toSelectableList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorRelationConductorViewModel(
    private val relationId: RelationId,
    private val getMotorRelationConductor: GetMotorRelationConductor,
    private val updateMotorRelationConductor: UpdateMotorRelationConductor,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
       = container(UiState()){
            intent {
                getMotorRelationConductor(relationId)
                   .map { toSelectableList(it) }.collect {
                       reduce { state.copy(items = it) }
                   }
            }
        }

    fun onValueChange(value:Conductor)=intent {
        updateMotorRelationConductor(relationId,value)
        navigationManager.back()
    }


}