package com.vm.eea.ui.motor.updateMotor.updateMotorPower

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.UnitOfPower
import com.vm.eea.domain.load.UpdateMotorPower
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.*
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorPowerViewModel(
    private val motorId: Long,
    private val getMotorPower: GetMotorPowerDetails,
    private val updateMotorPower: UpdateMotorPower,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState()){
             intent {
                 getMotorPower(motorId.toMotorId()).collect {
                 val newPower=state.power.copy(value = it.power.value.format(),second= it.power.unit)
                     reduce { state.copy(power = newPower)}
                 }
             }
    }

    fun onPowerChange(value:String,unit:UnitOfPower)=intent{
        val validationResult=Validator.validate.positiveNumber(value,"")
        val newPower=state.power.copy(value = value,second = unit,error = validationResult)
        reduce { state.copy(power = newPower,canSubmit = validationResult==null) }
    }

    fun submit()=intent{
        val power=state.power.value be state.power.second
        updateMotorPower(motorId.toMotorId(),power)
        navigationManager.back()
    }
}