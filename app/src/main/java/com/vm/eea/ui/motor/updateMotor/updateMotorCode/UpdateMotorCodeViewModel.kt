package com.vm.eea.ui.motor.updateMotor.updateMotorCode

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.load.UpdateMotorCode
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.motor.updateMotor.GetMotor
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.notNullOrBlank
import com.vm.eea.utilities.toMotorId
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorCodeViewModel(
    private val motorId:Long,
    private val getMotor: GetMotor,
    private val updateMotorCode: UpdateMotorCode,
    private val navigationManager: NavigationManager):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState()){
            intent {
                val motor=getMotor(motorId.toMotorId())
                reduce {
                    val code=state.code.copy(value = motor.code)
                    val description=state.description.copy(value = motor.description)
                    state.copy(code=code,description = description,canSubmit = true)
                }
            }
    }

    fun onCodeChange(value:String)=intent{
       val validationResult=Validator.validate.notNullOrBlank(value,"")
        val newCode=state.code.copy(value = value,error = validationResult)
        reduce { state.copy(code = newCode,canSubmit = validationResult==null) }
    }

    fun onDescriptionChange(value:String)=intent{
        val newDes=state.description.copy(value = value)
        reduce { state.copy(description = newDes) }
    }

    fun submit()=intent{
        updateMotorCode(motorId.toMotorId(),state.code.value,state.description.value)
        navigationManager.back()
    }
}