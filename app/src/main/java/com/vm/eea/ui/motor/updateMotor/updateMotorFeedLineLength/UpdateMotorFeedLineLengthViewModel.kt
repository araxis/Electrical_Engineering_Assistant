package com.vm.eea.ui.motor.updateMotor.updateMotorFeedLineLength

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.*
import com.vm.eea.domain.panelToMotorRelation.UpdateMotorFeedLineLength
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.positiveNumber
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


class UpdateMotorFeedLineLengthViewModel(
    private val relationId: RelationId,
    private val getMotorFeedLineLength: GetMotorFeedLineLength,
    private val updateMotorFeedLineLength: UpdateMotorFeedLineLength,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState()){
             intent {
                 getMotorFeedLineLength(relationId).collect {
                     val length=state.length.copy(value = it.length.value.format(),second = it.length.unit)
                     reduce { state.copy(length=length,canSubmit = true) }
                 }
             }
    }

    fun onValueChange(value:String,unit:UnitOfLength)=intent {
        val validationResult= Validator.validate.positiveNumber(value,"")
        val length=state.length.copy(value=value,second= unit,error = validationResult)
        reduce {
            state.copy(length = length,canSubmit = validationResult==null)
        }
    }

    fun submit()=intent {
        val length=state.length.value be state.length.second
        updateMotorFeedLineLength(relationId,length)
        navigationManager.back()
    }
}