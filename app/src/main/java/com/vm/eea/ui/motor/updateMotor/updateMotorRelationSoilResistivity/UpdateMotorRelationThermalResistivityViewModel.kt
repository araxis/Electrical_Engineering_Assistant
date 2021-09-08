package com.vm.eea.ui.motor.updateMotor.updateMotorRelationSoilResistivity

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.RelationId
import com.vm.eea.domain.UnitOfThermalResistivity
import com.vm.eea.domain.be
import com.vm.eea.domain.format
import com.vm.eea.domain.panelToMotorRelation.UpdateMotorRelationThermalResistivity
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorRelationThermalResistivityViewModel(
    private val relationId: RelationId,
    private val getMotorRelationThermalResistivity: GetMotorRelationThermalResistivity,
    private val updateMotorRelationGroundTemperature: UpdateMotorRelationThermalResistivity,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState()){
            intent {
                getMotorRelationThermalResistivity(relationId).collect {
                    val temperature=state.resistivity.copy(value = it.value.format())
                    reduce { state.copy(resistivity = temperature,canSubmit = true) }
                }


            }
    }

    fun onValueChange(value: String,unit:UnitOfThermalResistivity)=intent {
        val validatorResult=Validator.validate.inRange(value,1.0,100.0,"")
        val voltDrop=state.resistivity.copy(value=value,second= unit,error = validatorResult)
        reduce { state.copy(resistivity = voltDrop,canSubmit = validatorResult==null) }
    }

    fun submit()=intent {
        val value=state.resistivity.value be state.resistivity.second
        updateMotorRelationGroundTemperature(relationId,value)
        navigationManager.back()
    }
}