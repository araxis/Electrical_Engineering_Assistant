package com.vm.eea.ui.motor.updateMotor.updateEfficiency

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.Efficiency
import com.vm.eea.domain.format
import com.vm.eea.domain.load.UpdateMotorEfficiency
import com.vm.eea.domain.toMotorId
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateEfficiencyViewModel(
    private val motorId: Long,
    private val updateMotorEfficiency: UpdateMotorEfficiency,
    private val getMotorEfficiency: GetMotorEfficiency,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
        = container(UiState()){
            intent {
                getMotorEfficiency(motorId.toMotorId()).collect {
                    val efficiency=state.efficiency.copy(value = it.efficiency.value.format())
                    reduce { state.copy(efficiency = efficiency,canSubmit = true) }
                }
            }
    }

    fun onChange(value:String)=intent{
        val validatorResult=Validator.validate.inRange(value,.0,100.0,"")
        val newEfficiency=state.efficiency.copy(value=value,error = validatorResult)
        reduce { state.copy(efficiency = newEfficiency,canSubmit = validatorResult==null) }
    }

    fun submit()=intent {
        val efficiency=Efficiency(state.efficiency.value.toDouble())
        updateMotorEfficiency(motorId.toMotorId(),efficiency)
        navigationManager.back()
    }
}