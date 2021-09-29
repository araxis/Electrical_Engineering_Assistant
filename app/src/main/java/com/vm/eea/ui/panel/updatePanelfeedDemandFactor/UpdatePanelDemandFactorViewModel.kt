package com.vm.eea.ui.panel.updatePanelfeedDemandFactor

import androidx.lifecycle.ViewModel
import com.vm.eea.application.CosPhi
import com.vm.eea.application.PanelId
import com.vm.eea.application.format
import com.vm.eea.application.panel.IGetPanelDemandFactor
import com.vm.eea.application.panel.update.UpdatePanelDemandFactor
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdatePanelDemandFactorViewModel(
    private val panelId: PanelId,
    private val getInfo: IGetPanelDemandFactor,
    private val updateMotor: UpdatePanelDemandFactor,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
        = container(UiState()){
            onIO {
                val result=getInfo(panelId)
                intent {
                        val newPf=state.demandFactor.copy(value = result.value.format())
                        reduce { state.copy(demandFactor = newPf) }
                }
            }

    }

    fun onChange(value:String)=intent {
        val validationResult=Validator.validate.inRange(value,.1,1.0,"")
        val newPf=state.demandFactor.copy(value=value,error = validationResult)
        reduce { state.copy(demandFactor = newPf,canSubmit = validationResult==null) }
    }

    fun submit()=intent {
        val value= CosPhi(state.demandFactor.value.toDouble())
        updateMotor(panelId,value)
        navigationManager.back()
    }
}