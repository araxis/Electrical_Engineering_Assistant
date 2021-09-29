package com.vm.eea.ui.motor.updateMotor.updateMotorFeedLineLength

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Length
import com.vm.eea.application.RelationId
import com.vm.eea.application.be
import com.vm.eea.application.format
import com.vm.eea.application.panelToMotorRelation.IGetRelationLength
import com.vm.eea.application.panelToMotorRelation.UpdateMotorFeedLength
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.onIO
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


class UpdateMotorFeedLineLengthViewModel(
    private val relationId: RelationId,
    private val getMotorFeedLineLength: IGetRelationLength,
    private val updateMotorFeedLine: UpdateMotorFeedLength,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState()){
                onIO {
                    val length=getMotorFeedLineLength(relationId)
                    intent {
                        val lengthField=state.length.copy(value = length.value.format(),second = length.unit)
                        reduce { state.copy(length=lengthField,canSubmit = true) }
                    }
                }

    }

    fun onValueChange(value:String,unit: Length.Unit)=intent {
        val validationResult= Validator.validate.positiveNumber(value,"")
        val length=state.length.copy(value=value,second= unit,error = validationResult)
        reduce {
            state.copy(length = length,canSubmit = validationResult==null)
        }
    }

    fun submit()=intent {
        val length=state.length.value be state.length.second
        updateMotorFeedLine(relationId,length)
        navigationManager.back()
    }
}