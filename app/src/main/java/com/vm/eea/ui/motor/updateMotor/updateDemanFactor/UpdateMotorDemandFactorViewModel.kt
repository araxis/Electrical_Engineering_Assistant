package com.vm.eea.ui.motor.updateMotor.updateDemanFactor

import androidx.lifecycle.ViewModel
import com.vm.eea.application.CosPhi
import com.vm.eea.application.format
import com.vm.eea.application.motor.IGetMotorDemandFactor
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.motor.updateMotor.UpdateMotorDemandFactor
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorDemandFactorViewModel(
    private val motorId: MotorId,
    private val getMotorDemandFactor: IGetMotorDemandFactor,
    private val updateMotor: UpdateMotorDemandFactor,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
        = container(UiState()){
            onIO {
                val result=getMotorDemandFactor(motorId)
                intent {
                        val newPf=state.demandFactor.copy(value = result.value.format())
                        reduce { state.copy(demandFactor = newPf) }
                }
            }

    }

    fun onChange(value:String)=intent {
        val validationResult=Validator.validate.inRange(value,.1,1.0,"")
        val newPf=state.demandFactor.copy(value=value,error = validationResult)
        reduce { state.copy(demandFactor = newPf,canSubmit = validationResult==null) }
    }

    fun submit()=intent {
        val value= CosPhi(state.demandFactor.value.toDouble())
        updateMotor(motorId,value)
        navigationManager.back()
    }
}