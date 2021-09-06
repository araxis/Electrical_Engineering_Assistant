package com.vm.eea.ui.motor.motorDetails

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.MethodOfInstallation
import com.vm.eea.ui.MotorDestinations
import com.vm.eea.ui.NavigationCommand
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.PropertyItem
import com.vm.eea.utilities.toMotorId
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MotorDetailsViewModel(
    private val motorId: Long,
    private val getMotorDetails: GetMotorDetails,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState(emptyList())){
                intent {
                    getMotorDetails(motorId.toMotorId()).collect {
                        reduce { state.copy(items = map(it)) }
                    }
                }
    }

    fun onItemSelect(item:PropertyItem){
        navigationManager.navigate(item.updateViewRoute())
    }

    private fun map(motorDetails: MotorDetails):List<PropertyItem>{
        val groundTemperatureVisibility= motorDetails.relation.methodOfInstallation == MethodOfInstallation.A1

      return  listOf(
          PropertyItem("Code",motorDetails.motor.code,true) { MotorDestinations.UpdateMotorCode(motorId) },
          PropertyItem("Power",motorDetails.motor.power(),true) { MotorDestinations.UpdateMotorPower(motorId) },
          PropertyItem("Powerfactor",motorDetails.motor.powerfactor(),true) { MotorDestinations.UpdateMotorPowerfactor(motorId)},
          PropertyItem("Demand Factor",motorDetails.motor.demandFactor(),true) { MotorDestinations.UpdateMotorDemandFactor(motorId) },
          PropertyItem("Efficiency",motorDetails.motor.efficiency(),true) { NavigationCommand("") },
          PropertyItem("Feeder",motorDetails.feeder.code,true) { MotorDestinations.UpdateMotorFeeder(motorId) },
          PropertyItem("Line length",motorDetails.relation.length(),true) { NavigationCommand("") },
          PropertyItem("Method of installation",motorDetails.relation.methodOfInstallation(),true) {NavigationCommand("")},
          PropertyItem("Max voltage drop",motorDetails.relation.maxVoltageDrop(),true) {NavigationCommand("")},
          PropertyItem("Ambient temperature",motorDetails.relation.ambientTemperature(),true) {NavigationCommand("")},
          PropertyItem("Ground temperature",motorDetails.relation.groundTemperature(),groundTemperatureVisibility) {NavigationCommand("")},
          PropertyItem("Soil thermal resistivity",motorDetails.relation.soilThermalResistivity(),groundTemperatureVisibility) {NavigationCommand("")},
          PropertyItem("Conductor",motorDetails.relation.conductor(),true) { NavigationCommand("") },
          PropertyItem("Insulation",motorDetails.relation.insulation(),true) { NavigationCommand("") },
          PropertyItem("Circuits in the same conduit",motorDetails.relation.circuitCount(),true) {
              NavigationCommand("")
          },
      )
    }
}