package com.vm.eea.ui.motor.updateMotor.updateMotorRelationMaxVoltDrop

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.RelationId
import com.vm.eea.domain.VoltDrop
import com.vm.eea.domain.format
import com.vm.eea.domain.panelToMotorRelation.UpdateMotorRelationMaxVoltDrop
import com.vm.eea.ui.NavigationManager

import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorRelationMaxVoltDropViewModel(
    private val relationId: RelationId,
    private val getMotorRelationMaxVoltDrop: GetMotorRelationMaxVoltDrop,
    private val updateMotorRelationMaxVoltDrop: UpdateMotorRelationMaxVoltDrop,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState()){
            intent {
                getMotorRelationMaxVoltDrop(relationId).collect {
                    val voltDrop=state.voltDrop.copy(value = it.value.format())
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
        updateMotorRelationMaxVoltDrop(relationId, VoltDrop(state.voltDrop.value.toDouble()))
        navigationManager.back()
    }
}