package com.vm.eea.ui.motor.updateMotor.updateMotorPower

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Power
import com.vm.eea.application.be
import com.vm.eea.application.format
import com.vm.eea.application.motor.IGetMotorPower
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.motor.updateMotor.UpdateMotorPower
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.*
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorPowerViewModel(
    private val motorId: MotorId,
    private val getMotorPower: IGetMotorPower,
    private val updateMotor: UpdateMotorPower,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState()){
           onIO {
               val powerValue=getMotorPower(motorId)
               intent {

                       val newPower=state.power.copy(value = powerValue.value.format(),second= powerValue.unit)
                       reduce { state.copy(power = newPower)}

               }
           }

    }

    fun onPowerChange(value:String,unit: Power.Unit)=intent{
        val validationResult=Validator.validate.positiveNumber(value,"")
        val newPower=state.power.copy(value = value,second = unit,error = validationResult)
        reduce { state.copy(power = newPower,canSubmit = validationResult==null) }
    }

    fun submit()=intent{
        val power=state.power.value be state.power.second
        updateMotor(motorId,power)
        navigationManager.back()
    }
}