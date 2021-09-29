package com.vm.eea.ui.panel.updatePanelFeedTemperature

import androidx.lifecycle.ViewModel
import com.vm.eea.application.*
import com.vm.eea.ui.NavigationManager
import com.vm.eea.application.panelToPanelRelation.IGetPanelFeedTemperature
import com.vm.eea.application.panelToPanelRelation.UpdatePanelFeedTemperature
import com.vm.eea.utilities.*
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelFeedTemperatureViewModel(
    private val relationId: RelationId,
    private val environment: Environment,
    private val updatePanelFeed: UpdatePanelFeedTemperature,
    private val getInfo: IGetPanelFeedTemperature,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing> ,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init(environment)){
              onIO {
                  val current=getInfo(relationId,environment )
                  intent {reduce { state.copy(value =current.value.format(),unit = current.unit)}}}



    }

    fun onValueChange(value:String, unit: UnitOfTemperature)=intent{
       val validationResult=Validator.validate.positiveNumber(value,"")
        reduce {
            state.copy(value=value,unit=unit,
                error = validationResult?.let { SimpleText(it.message) },
                canExecute = validationResult==null)
        }
    }



   fun onSubmit()=intent{
       val value=state.value be state.unit
       updatePanelFeed(relationId,value,environment )
       navigationManager.back()
    }
}