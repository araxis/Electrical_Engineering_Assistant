package com.vm.eea.ui.motor.updateMotor.updateEfficiency

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Efficiency
import com.vm.eea.application.format
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.motor.updateMotor.UpdateMotorEfficiency
import com.vm.eea.data.motor.GetMotorEfficiency
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateEfficiencyViewModel(
    private val motorId: MotorId,
    private val updateMotor: UpdateMotorEfficiency,
    private val getMotorEfficiency: GetMotorEfficiency,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
        = container(UiState()){
                onIO {
                   val efficiency=getMotorEfficiency(motorId)
                    intent {
                        val efficiencyState=state.efficiency.copy(value = efficiency.value.format())
                        reduce { state.copy(efficiency = efficiencyState,canSubmit = true) }
                    }
                }

    }

    fun onChange(value:String)=intent{
        val validatorResult=Validator.validate.inRange(value,.0,100.0,"")
        val newEfficiency=state.efficiency.copy(value=value,error = validatorResult)
        reduce { state.copy(efficiency = newEfficiency,canSubmit = validatorResult==null) }
    }

    fun submit()=intent {
        val efficiency= Efficiency(state.efficiency.value.toDouble())
        updateMotor(motorId,efficiency)
        navigationManager.back()
    }
}