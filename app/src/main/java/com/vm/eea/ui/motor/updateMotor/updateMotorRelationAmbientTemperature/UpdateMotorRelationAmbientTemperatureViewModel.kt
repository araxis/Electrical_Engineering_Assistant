package com.vm.eea.ui.motor.updateMotor.updateMotorRelationAmbientTemperature

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.RelationId
import com.vm.eea.domain.UnitOfTemperature
import com.vm.eea.domain.be
import com.vm.eea.domain.format
import com.vm.eea.domain.panelToMotorRelation.UpdateMotorRelationAmbientTemperature
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorRelationAmbientTemperatureViewModel(
    private val relationId: RelationId,
    private val getMotorRelationMaxVoltDrop: GetMotorRelationAmbientTemperature,
    private val updateMotorRelationAmbientTemperature: UpdateMotorRelationAmbientTemperature,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState()){
            intent {
                getMotorRelationMaxVoltDrop(relationId).collect {
                    val temperature=state.temperature.copy(value = it.value.format())
                    reduce { state.copy(temperature = temperature,canSubmit = true) }
                }


            }
    }

    fun onValueChange(value: String,unit:UnitOfTemperature)=intent {
        val validatorResult=Validator.validate.inRange(value,1.0,100.0,"")
        val voltDrop=state.temperature.copy(value=value,second= unit,error = validatorResult)
        reduce { state.copy(temperature = voltDrop,canSubmit = validatorResult==null) }
    }

    fun submit()=intent {
        val temperature=state.temperature.value be state.temperature.second
        updateMotorRelationAmbientTemperature(relationId,temperature)
        navigationManager.back()
    }
}