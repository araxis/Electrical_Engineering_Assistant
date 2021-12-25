package com.vm.eea.ui.panel.panelResult

import androidx.lifecycle.ViewModel
import com.vm.eea.application.PanelId
import com.vm.eea.application.calculators.panelFullResult.PanelCalculationResult
import com.vm.eea.application.calculators.panelFullResult.PanelCalculator
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.ui.GroupPropertyItem
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.motor.motorResult.Effect
import com.vm.eea.utilities.onIO
import com.vm.eea.utilities.propertyItem
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PanelResultViewModel(
    private val panelId: PanelId,
    private val panelCalculator: PanelCalculator,
    private val motorRepository: IMotorRepository,
    private val navigationManager: NavigationManager
):ContainerHost<UiState, Effect>,ViewModel() {
    override val container: Container<UiState, Effect>
         = container(UiState()){
             onIO {
                 val result=panelCalculator(panelId)
                 val properties=mapToPropertyList(result)
                 val motors=motorRepository.getPanelMotors(panelId)
                 val report=LoadListGenerator().generate(result,motors)
                 intent {
                     reduce { state.copy(resultItems = properties, report = report) }
                 }
             }
    }


    private fun mapToPropertyList(result: PanelCalculationResult): MutableList<GroupPropertyItem> {
        val cat1 = "Calculated parameters"
        val spec="Specifications"
        val specItems= listOf(
            result.lineNullVoltage.propertyItem("L-N voltage",spec),
            result.lineLineVoltage.propertyItem("L-L voltage",spec),
            result.demandFactor.propertyItem("Desired power factor",spec),
            result.coincidenceFactor.propertyItem("Coincidence factor",spec)

        )
        val calcItems=listOf(
            result.current.propertyItem("Total current", cat1),
            result.activePower.propertyItem("Total active power", cat1),
            result.apparentPower.propertyItem("Total apparent power", cat1),
            result.reactivePower.propertyItem("Total reactive power", cat1),
            result.cosPhi.propertyItem("Power factor", cat1),
            result.protection.propertyItem("Circuit breaker", cat1),
            result.requiredReactivePower.propertyItem("Required kVar", cat1),
        )
        return mutableListOf(
            GroupPropertyItem(key = spec,value=spec,specItems),
            GroupPropertyItem(key = cat1, value = cat1,calcItems ))
    }

    fun share()=intent{
        postSideEffect(Effect.Share())
    }
    fun preview()=intent{
        reduce { state.copy(preview = true) }
    }
    fun onBack()=intent {
        if(state.preview){
            reduce { state.copy(preview = false) }
        }else{
            navigationManager.back()
        }
    }
}