package com.vm.eea.ui.motor.updateMotor.updateMotorRelationTemperature

import androidx.lifecycle.ViewModel
import com.vm.eea.application.*
import com.vm.eea.application.panelToMotorRelation.IGetMotorFeedTemperature
import com.vm.eea.application.panelToMotorRelation.UpdateMotorFeedTemperature
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorRelationTemperatureViewModel(
    private val relationId: RelationId,
    private val environment: Environment,
    private val getMotorFeedTemperature: IGetMotorFeedTemperature,
    private val updatePanelToMotorFeed: UpdateMotorFeedTemperature,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState.init(environment)){
            onIO {
                val result=getMotorFeedTemperature(relationId,environment)
                intent {
                        val temperature=state.temperature.copy(value = result.value.format())
                        reduce { state.copy(temperature = temperature,canSubmit = true) }
                }
            }

    }

    fun onValueChange(value: String,unit: UnitOfTemperature)=intent {
        val validatorResult=Validator.validate.inRange(value,1.0,100.0,"")
        val voltDrop=state.temperature.copy(value=value,second= unit,error = validatorResult)
        reduce { state.copy(temperature = voltDrop,canSubmit = validatorResult==null) }
    }

    fun submit()=intent {
        val temperature=state.temperature.value be state.temperature.second
        updatePanelToMotorFeed(relationId,temperature,environment)
        navigationManager.back()
    }
}