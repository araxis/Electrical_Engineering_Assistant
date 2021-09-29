package com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor

import androidx.lifecycle.ViewModel
import com.vm.eea.application.CosPhi
import com.vm.eea.application.format
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.motor.updateMotor.UpdateMotorCosPhi
import com.vm.eea.data.motor.GetMotorPowerfactorDetail
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


class UpdateMotorPowerFactorViewModel(
    private val motorId: MotorId,
    private val getMotorPowerfactorDetail: GetMotorPowerfactorDetail,
    private val updateMotor: UpdateMotorCosPhi,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {


    override val container: Container<UiState, Nothing>
         = container(UiState()){
            onIO {
                val pf= getMotorPowerfactorDetail(motorId)
                intent {

                        val newPf=state.demandFactor.copy(value = pf.value.format())
                        reduce { state.copy(demandFactor = newPf) }

                }
            }


    }

    fun onChange(value: String)=intent {
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val newPf=state.demandFactor.copy(value = value,error = validationResult)
        reduce { state.copy(demandFactor = newPf,canSubmit = validationResult==null) }
    }

    fun submit()=intent {
        updateMotor(motorId, CosPhi(state.demandFactor.value.toDouble()))
        navigationManager.back()
    }
}