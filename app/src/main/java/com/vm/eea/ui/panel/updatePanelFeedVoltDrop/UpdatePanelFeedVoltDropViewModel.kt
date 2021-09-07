package com.vm.eea.ui.panel.updatePanelFeedVoltDrop

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.VoltDrop
import com.vm.eea.domain.panelToPanelRelation.GetFeedingRelationByRelation
import com.vm.eea.domain.panelToPanelRelation.UpdatePanelFeed
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.SimpleText
import com.vm.eea.utilities.Validator
import com.vm.eea.domain.format
import com.vm.eea.utilities.positiveNumber
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelFeedVoltDropViewModel(
    private val relationId: Long,
    private val updatePanelFeed: UpdatePanelFeed,
    private val getFeedingRelationByRelation: GetFeedingRelationByRelation,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init()){
             intent {
                 getFeedingRelationByRelation(relationId).collect {
                     reduce { state.copy(value = it.maxVoltageDrop.value.format(),canExecute = true) }
                 }
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