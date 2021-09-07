package com.vm.eea.ui.panel.panelDetails

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.Environment
import com.vm.eea.domain.MethodOfInstallation
import com.vm.eea.domain.panel.GetPanel
import com.vm.eea.domain.panel.Panel
import com.vm.eea.domain.panelToPanelRelation.GetFeedingRelationByConsumer
import com.vm.eea.domain.panelToPanelRelation.PanelToPanelRelation
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.PanelDestinations
import com.vm.eea.ui.PropertyItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PanelDetailsViewModel(
    private val panelId: Long,
    private val getPanel: GetPanel,
    private val getFeedingRelationByConsumer: GetFeedingRelationByConsumer,
    private val navigationManager: NavigationManager,
) :ContainerHost<UiState,Nothing>,ViewModel(){



    override val container: Container<UiState, Nothing>
         = container(UiState.init()){
        intent {
            getPanel(panelId).combine(getFeedingRelationByConsumer(panelId)){ panel, relation->

               mapToPropertyList(panel,relation)
            }.collect {
                reduce { state.copy(items = it) }
            }

        }

    }

    fun onItemSelect(item: PropertyItem){
        navigationManager.navigate(item.updateViewRoute())
    }

    private fun mapToPropertyList(item: Panel,relation: PanelToPanelRelation):List<PropertyItem>{
        return listOf(
           PropertyItem("Code",item.code) { PanelDestinations.UpdatePanelCode(panelId) },
           PropertyItem("Feeder",relation.fromPanel.code) { PanelDestinations.UpdatePanelFeeder(panelId) },
           PropertyItem("Length",relation.length()) { PanelDestinations.UpdatePanelFeedLength(relation.id.id) },
           PropertyItem("Method od installation",relation.methodOfInstallation()) { PanelDestinations.UpdatePanelFeedMethodOfInstallation(relation.id.id)},
           PropertyItem("Max voltage drop",relation.maxVoltageDrop()) { PanelDestinations.UpdatePanelFeedVoltDrop(relation.id.id)},
           PropertyItem("Ambient temperature",relation.ambientTemperature(),visible =relation.methodOfInstallation== MethodOfInstallation.A1) {
               PanelDestinations.UpdatePanelFeedTemperature(relation.id.id, Environment.Ambient) },
           PropertyItem("Ground temperature",relation.groundTemperature(),visible =relation.methodOfInstallation!= MethodOfInstallation.A1) {
               PanelDestinations.UpdatePanelFeedTemperature(relation.id.id, Environment.Ground) },
           PropertyItem("Soil thermal resistivity",relation.soilThermalResistivity(),visible =relation.methodOfInstallation!= MethodOfInstallation.A1) {
               PanelDestinations.UpdatePanelFeedSoilResistivity(relation.id.id) },
           PropertyItem("Conductor",relation.conductor()) {
               PanelDestinations.UpdatePanelFeedConductor(relation.id.id)},
           PropertyItem("Insulation",relation.insulation()) {
               PanelDestinations.UpdatePanelFeedInsulation(relation.id.id)},
            PropertyItem("Circuits in the same conduit",relation.circuitCount()) {
                PanelDestinations.UpdatePanelFeedCircuitCount(relation.id.id)},
            )
    }


}