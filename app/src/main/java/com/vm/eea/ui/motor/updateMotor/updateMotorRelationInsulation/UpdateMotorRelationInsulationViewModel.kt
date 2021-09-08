package com.vm.eea.ui.motor.updateMotor.updateMotorRelationInsulation

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.Insulation
import com.vm.eea.domain.MethodOfInstallation
import com.vm.eea.domain.RelationId
import com.vm.eea.domain.panelToMotorRelation.UpdateMotorRelationInsulation
import com.vm.eea.domain.panelToMotorRelation.UpdateMotorRelationMethodOfInstallation
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.toSelectableList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorRelationInsulationViewModel(
    private val relationId: RelationId,
    private val getMotorRelationInsulation: GetMotorRelationInsulation,
    private val updateMotorRelationInsulation: UpdateMotorRelationInsulation,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
       = container(UiState()){
            intent {
               getMotorRelationInsulation(relationId)
                   .map { toSelectableList(it) }.collect {
                       reduce { state.copy(items = it) }
                   }
            }
        }

    fun onValueChange(value:Insulation)=intent {
        updateMotorRelationInsulation(relationId,value)
        navigationManager.back()
    }


}