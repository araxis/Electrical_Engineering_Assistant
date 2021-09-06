package com.vm.eea.ui.panel.updatePanelFeedLength

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.UnitOfLength
import com.vm.eea.domain.panelToPanelRelation.GetFeedingRelationByRelation
import com.vm.eea.domain.panelToPanelRelation.UpdatePanelFeed
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.*
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelFeedLengthViewModel(
    private val relationId: Long,
    private val updatePanelFeedLength: UpdatePanelFeed,
    private val getFeedingRelationByRelation: GetFeedingRelationByRelation,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init()){
             intent {
                 getFeedingRelationByRelation(relationId).collect {
                     reduce { state.copy(value = it.length.value.format(),unit = it.length.unit,canExecute = true) }
                 }
             }
    }

    fun onLengthChange(value:String,unit:UnitOfLength)=intent{
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