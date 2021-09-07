package com.vm.eea.ui.panel.updatePanelFeedTemperature

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.*
import com.vm.eea.domain.defaultGroundTemperature.GetDefaultTemperatures
import com.vm.eea.domain.panelToPanelRelation.GetFeedingRelationByRelation
import com.vm.eea.domain.panelToPanelRelation.UpdatePanelFeed
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelFeedTemperatureViewModel(
    private val relationId: Long,
    private val environment: Environment,
    private val updatePanelFeed: UpdatePanelFeed,
    private val getFeedingRelationByRelation: GetFeedingRelationByRelation,
    private val getDefaultTemperatures: GetDefaultTemperatures,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init(environment)){
             intent {
                 getFeedingRelationByRelation(relationId)
                     .combine(getDefaultTemperatures(environment)){relation,defaults->
                         relation to defaults
                     }
                     .collect {

                     val value=when(environment){
                         Environment.Ambient -> it.first.ambientTemperature
                         Environment.Ground -> it.first.groundTemperature
                     }
                     reduce { state.copy(value =value.value.format(),unit = value.unit,canExecute = true,
                         defaults = it.second.map {o-> SelectableItem(o.value,o.value==value)}) }
                 }
             }
    }

    fun onValueChange(value:String, unit: UnitOfTemperature)=intent{
       val validationResult=Validator.validate.positiveNumber(value,"")
        reduce {
            state.copy(value=value,unit=unit,
                error = validationResult?.let { SimpleText(it.message) },
                canExecute = validationResult==null)
        }
    }

    fun onItemSelect(item: Temperature)=intent{
        updatePanelFeed(relationId,item,environment)
        navigationManager.back()
    }

   fun onSubmit()=intent{
       val value=state.value be state.unit
       updatePanelFeed(relationId,value,environment)
       navigationManager.back()
    }
}