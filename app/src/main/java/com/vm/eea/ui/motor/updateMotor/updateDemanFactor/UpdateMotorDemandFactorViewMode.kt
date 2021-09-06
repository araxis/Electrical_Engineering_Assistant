package com.vm.eea.ui.motor.updateMotor.updateDemanFactor

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.PowerFactor
import com.vm.eea.domain.load.UpdateMotorDemandFactor
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.format
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.toMotorId
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorDemandFactorViewMode(
    private val motorId: Long,
    private val getMotorDemandFactor: GetMotorDemandFactor,
    private val updateMotorDemandFactor: UpdateMotorDemandFactor,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
        = container(UiState()){
            intent {

                getMotorDemandFactor(motorId.toMotorId()).collect {
                    val newPf=state.powerfactor.copy(value = it.demandFactor.value.format())
                    reduce { state.copy(powerfactor = newPf) }
                }
            }
    }

    fun onChange(value:String)=intent {
        val validationResult=Validator.validate.inRange(value,.1,1.0,"")
        val newPf=state.powerfactor.copy(value=value,error = validationResult)
        reduce { state.copy(powerfactor = newPf,canSubmit = validationResult==null) }
    }

    fun submit()=intent {
        val value=PowerFactor(state.powerfactor.value.toDouble())
        updateMotorDemandFactor(motorId.toMotorId(),value)
        navigationManager.back()
    }
}