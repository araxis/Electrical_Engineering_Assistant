package com.vm.eea.ui.panel.panelDetails

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Environment
import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.PanelId
import com.vm.eea.application.panel.IGetPanelDetails
import com.vm.eea.application.panel.PanelDetails
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.PanelDestinations
import com.vm.eea.ui.PropertyItem
import com.vm.eea.utilities.onIO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PanelDetailsViewModel(
    private val panelId: PanelId,
    private val getPanelDetails: IGetPanelDetails,
    private val navigationManager: NavigationManager,
) :ContainerHost<UiState,Nothing>,ViewModel(){



    override val container: Container<UiState, Nothing>
         = container(UiState.init()){
        onIO {
            getPanelDetails(panelId)
                .map { mapToPropertyList(it) }
                .collect {
                    intent { reduce { state.copy(items = it) } }
                }

        }


    }

    fun onItemSelect(item: PropertyItem){
        navigationManager.navigate(item.updateViewRoute())
    }

    private fun mapToPropertyList(item: PanelDetails):List<PropertyItem>{
        return listOf(
           PropertyItem("Code",item.code) { PanelDestinations.UpdatePanelCode(panelId) },
           PropertyItem("Feeder",item.feederCode) { PanelDestinations.UpdatePanelFeeder(panelId) },
           PropertyItem("Length",item.length.toFormatString()) { PanelDestinations.UpdatePanelFeedLength(item.relationId) },
           PropertyItem("Method od installation",item.methodOfInstallation()) { PanelDestinations.UpdatePanelFeedMethodOfInstallation(item.relationId)},
           PropertyItem("Max voltage drop",item.maxVoltageDrop()) { PanelDestinations.UpdatePanelFeedVoltDrop(item.relationId)},
           PropertyItem("Ambient temperature",item.ambientTemperature(),visible =item.methodOfInstallation== MethodOfInstallation.A1) {
               PanelDestinations.UpdatePanelFeedTemperature(item.relationId, Environment.Ambient) },
           PropertyItem("Ground temperature",item.groundTemperature(),visible =item.methodOfInstallation!= MethodOfInstallation.A1) {
               PanelDestinations.UpdatePanelFeedTemperature(item.relationId, Environment.Ground) },
           PropertyItem("Soil thermal resistivity",item.soilThermalResistivity(),visible =item.methodOfInstallation!= MethodOfInstallation.A1) {
               PanelDestinations.UpdatePanelFeedSoilResistivity(item.relationId) },
           PropertyItem("Conductor",item.conductor()) {
               PanelDestinations.UpdatePanelFeedConductor(item.relationId)},
           PropertyItem("Insulation",item.insulation()) {
               PanelDestinations.UpdatePanelFeedInsulation(item.relationId)},
            PropertyItem("Circuits in the same conduit",item.circuitCount.toString()) {
                PanelDestinations.UpdatePanelFeedCircuitCount(item.relationId)},
            )
    }


}