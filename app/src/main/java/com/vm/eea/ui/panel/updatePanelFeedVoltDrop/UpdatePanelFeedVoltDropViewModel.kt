package com.vm.eea.ui.panel.updatePanelFeedVoltDrop

import androidx.lifecycle.ViewModel
import com.vm.eea.application.RelationId
import com.vm.eea.application.VoltDrop
import com.vm.eea.application.format
import com.vm.eea.application.panelToPanelRelation.IGetPanelFeedMaxVoltDrop
import com.vm.eea.application.panelToPanelRelation.UpdatePanelFeedMaxVoltDrop
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

class UpdatePanelFeedVoltDropViewModel(
    private val relationId: RelationId,
    private val updatePanelFeed: UpdatePanelFeedMaxVoltDrop,
    private val getInfo: IGetPanelFeedMaxVoltDrop,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init()){
             onIO {
                 val current=getInfo(relationId)
                 intent {reduce { state.copy(value = current.value.format(),canExecute = true) }}
             }

    }

    fun onValueChange(value:String)=intent{
       val validationResult=Validator.validate.positiveNumber(value,"")
        reduce {
            state.copy(value=value,
                error = validationResult?.let { SimpleText(it.message) },
                canExecute = validationResult==null)
        }
    }

   fun onSubmit()=intent{
       updatePanelFeed(relationId, VoltDrop(state.value.toDouble()))
       navigationManager.back()
    }
}