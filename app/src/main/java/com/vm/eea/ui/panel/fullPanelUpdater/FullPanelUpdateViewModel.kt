package com.vm.eea.ui.panel.fullPanelUpdater

import androidx.lifecycle.ViewModel
import com.vm.eea.application.PanelId
import com.vm.eea.application.panel.IPanelRepository
import com.vm.eea.application.panel.Panel
import com.vm.eea.application.panel.PanelCode
import com.vm.eea.ui.CoincidenceFactorField
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.StringField
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.notNullOrBlank
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FullPanelUpdateViewModel(private val panelId: PanelId,
                               private val panelRepository: IPanelRepository ,
                               private val navigationManager: NavigationManager):ContainerHost<UiState,Nothing>,ViewModel() {
    lateinit var panel:Panel
    override val container: Container<UiState, Nothing>
         = container(UiState()){
             onIO {
                 panel=panelRepository.get(panelId)
                 intent {
                     reduce {
                         state.copy(code = StringField.valid(value = panel.code.value),
                         demandFactor = CosPhiField.valid("Demand factor",panel.demandFactor),
                         coincidenceFactor = CoincidenceFactorField.valid(value = panel.coincidenceFactor))
                     }
                 }
             }
    }

    fun onCoincidenceFactorChange(value:String)=intent{
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val fieldState=state.coincidenceFactor.copy(input= value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(coincidenceFactor = fieldState)
        updateCalculationState(newState)
    }
    fun onDemandFactorChange(value: String)=intent {
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val fieldState=state.demandFactor.copy(input= value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(demandFactor = fieldState)
        updateCalculationState(newState)
    }

    private fun isValid(state:UiState):Boolean{
        if(state.code.notValid) return false
        if(state.demandFactor.notValid) return false
        if(state.coincidenceFactor.notValid) return false

        return true
    }

    fun onCodeChange(value: String)=intent {
        val validationResult= Validator.validate.notNullOrBlank(value,"")
        val fieldState=state.code.copy(value= value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(code = fieldState)
        updateCalculationState(newState)
    }

    private fun updateCalculationState(newState: UiState)=intent {
        val canCalculate=isValid(newState)
        val formState=newState.copy(canCalculate = canCalculate)
        reduce { formState }
    }


    fun update()=intent {
        val code= PanelCode(state.code.value)
        val demandFactor=state.demandFactor.value
        val coincidenceFactor=state.coincidenceFactor.value
        val updatePanel=panel.copy(code = code,demandFactor = demandFactor,coincidenceFactor = coincidenceFactor)
        onIO {
            panelRepository.update(updatePanel)
        }
        navigationManager.back()
    }


}