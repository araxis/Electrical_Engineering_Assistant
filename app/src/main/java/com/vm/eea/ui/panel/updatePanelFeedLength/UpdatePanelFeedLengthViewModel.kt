package com.vm.eea.ui.panel.updatePanelFeedLength

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Length
import com.vm.eea.application.RelationId
import com.vm.eea.application.be
import com.vm.eea.application.format
import com.vm.eea.application.panelToPanelRelation.IGetPanelFeedLength
import com.vm.eea.application.panelToPanelRelation.UpdatePanelFeedLength
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.SimpleText
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.onIO
import com.vm.eea.utilities.positiveNumber
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelFeedLengthViewModel(
    private val relationId: RelationId,
    private val updatePanelFeedLength: UpdatePanelFeedLength,
    private val getInfo: IGetPanelFeedLength,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init()){
                onIO {
                    val current=getInfo(relationId)
                    intent {reduce { state.copy(value = current.value.format(),unit = current.unit,canExecute = true) }}
                }

    }

    fun onLengthChange(value:String,unit: Length.Unit)=intent{
       val validationResult=Validator.validate.positiveNumber(value,"")
        reduce {
            state.copy(value=value,
                error = validationResult?.let { SimpleText(it.message) },
                canExecute = validationResult==null)
        }
    }

   fun onSubmit()=intent{
       val length=state.value be state.unit
       updatePanelFeedLength(relationId,length)
       navigationManager.back()
    }
}