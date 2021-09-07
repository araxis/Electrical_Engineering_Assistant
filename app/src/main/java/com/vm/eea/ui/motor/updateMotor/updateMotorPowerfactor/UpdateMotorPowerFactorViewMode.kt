package com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.PowerFactor
import com.vm.eea.domain.load.UpdateMotorPowerfactor
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.domain.format
import com.vm.eea.utilities.inRange
import com.vm.eea.domain.toMotorId
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


class UpdateMotorPowerFactorViewMode(
    private val motorId: Long,
    private val getMotorPowerfactorDetail: GetMotorPowerfactorDetail,
    private val updateMotorPowerFactor: UpdateMotorPowerfactor,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {


    override val container: Container<UiState, Nothing>
         = container(UiState()){
        intent {
            getMotorPowerfactorDetail(motorId.toMotorId()).collect {
                val newPf=state.powerfactor.copy(value = it.powerfactor.value.format())
                reduce { state.copy(powerfactor = newPf) }
            }
        }

    }

    fun onChange(value: String)=intent {
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val newPf=state.powerfactor.copy(value = value,error = validationResult)
        reduce { state.copy(powerfactor = newPf,canSubmit = validationResult==null) }
    }

    fun submit()=intent {
        updateMotorPowerFactor(motorId.toMotorId(), PowerFactor(state.powerfactor.value.toDouble()))
        navigationManager.back()
    }
}