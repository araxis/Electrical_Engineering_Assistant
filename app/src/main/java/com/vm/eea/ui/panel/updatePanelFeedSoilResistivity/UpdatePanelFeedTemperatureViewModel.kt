package com.vm.eea.ui.panel.updatePanelFeedSoilResistivity

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.ThermalResistivity
import com.vm.eea.domain.UnitOfThermalResistivity
import com.vm.eea.domain.defaultSiolResistivity.GetDefaultSoilResistivity
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

class UpdatePanelFeedSoilResistivityViewModel(
    private val relationId: Long,
    private val updatePanelFeed: UpdatePanelFeed,
    private val getFeedingRelationByRelation: GetFeedingRelationByRelation,
    private val getDefaultSoilResistivity: GetDefaultSoilResistivity,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init()){
             intent {
                 getFeedingRelationByRelation(relationId)
                     .combine(getDefaultSoilResistivity()){relation,defaults->
                         relation to defaults
                     }
                     .collect {

                   val value=it.first.soilThermalResistivity
                     reduce { state.copy(value =value.value.format(),unit =value.unit,canExecute = true,
                         defaults = it.second.map {o-> SelectableItem(o.value,o.value==value)}) }
                 }
             }
    }

    fun onValueChange(value:String, unit:UnitOfThermalResistivity)=intent{
       val validationResult=Validator.validate.positiveNumber(value,"")
        reduce {
            state.copy(value=value,unit=unit,
                error = validationResult?.let { SimpleText(it.message) },
                canExecute = validationResult==null)
        }
    }

    fun onItemSelect(item:ThermalResistivity)=intent{
        updatePanelFeed(relationId,item)
        navigationManager.back()
    }

   fun onSubmit()=intent{
       val value=state.value be state.unit
       updatePanelFeed(relationId,value)
       navigationManager.back()
    }
}