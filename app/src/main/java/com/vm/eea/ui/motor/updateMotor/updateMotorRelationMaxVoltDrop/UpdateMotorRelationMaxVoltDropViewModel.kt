package com.vm.eea.ui.motor.updateMotor.updateMotorRelationMaxVoltDrop

import androidx.lifecycle.ViewModel
import com.vm.eea.application.RelationId
import com.vm.eea.application.VoltDrop
import com.vm.eea.application.format
import com.vm.eea.application.panelToMotorRelation.IGetMotorFeedMaxVoltDrop
import com.vm.eea.application.panelToMotorRelation.UpdateMotorFeedMaxVoltDrop
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorRelationMaxVoltDropViewModel(
    private val relationId: RelationId,
    private val getMotorRelationMaxVoltDrop: IGetMotorFeedMaxVoltDrop,
    private val updatePanelToMotorFeed: UpdateMotorFeedMaxVoltDrop,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState()){
            onIO {
                val value=getMotorRelationMaxVoltDrop(relationId)
                intent {

                        val voltDrop=state.voltDrop.copy(value = value.value.format())
                        reduce { state.copy(voltDrop = voltDrop,canSubmit = true) }
            }

            }
    }

    fun onValueChange(value: String)=intent {
        val validatorResult=Validator.validate.inRange(value,1.0,100.0,"")
        val voltDrop=state.voltDrop.copy(value=value,error = validatorResult)
        reduce { state.copy(voltDrop = voltDrop,canSubmit = validatorResult==null) }
    }

    fun submit()=intent {
        updatePanelToMotorFeed(relationId, VoltDrop(state.voltDrop.value.toDouble()))
        navigationManager.back()
    }
}