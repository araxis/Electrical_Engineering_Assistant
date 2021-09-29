package com.vm.eea.ui.motor.motorDetails

import androidx.lifecycle.ViewModel
import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.motor.IGetMotorDetails
import com.vm.eea.application.motor.MotorDetails
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.Environment
import com.vm.eea.ui.MotorDestinations
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.PropertyItem
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MotorDetailsViewModel(
    private val motorId: MotorId,
    private val getMotorDetails: IGetMotorDetails,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState(emptyList())){
                intent {
                    getMotorDetails(motorId).collect {
                        reduce { state.copy(items = map(it),calculatedInfo = mapCalculatedFields(it)) }
                    }
                }
    }

    fun onItemSelect(item:PropertyItem){
        navigationManager.navigate(item.updateViewRoute())
    }

    private fun map(motorDetails: MotorDetails):List<PropertyItem>{
        val groundTemperatureVisibility= motorDetails.methodOfInstallation == MethodOfInstallation.A1
      return  listOf(
          PropertyItem("Code",motorDetails.code,true) { MotorDestinations.UpdateMotorCode(motorId) },
          PropertyItem("Power",motorDetails.power.toFormatString(),true) { MotorDestinations.UpdateMotorPower(motorId) },
          PropertyItem("Start mode",motorDetails.startMode.toString(),true) { MotorDestinations.UpdateMotorStartMode(motorId) },
          PropertyItem("Powerfactor",motorDetails.cosPhi.toFormatString(),true) { MotorDestinations.UpdateMotorPowerfactor(motorId)},
          PropertyItem("Demand Factor",motorDetails.demandFactor.toFormatString(),true) { MotorDestinations.UpdateMotorDemandFactor(motorId) },
          PropertyItem("Efficiency",motorDetails.efficiency.toFormatString(),true) { MotorDestinations.UpdateMotorEfficiency(motorId)},
          PropertyItem("Feeder",motorDetails.feederCode,true) { MotorDestinations.UpdateMotorFeeder(motorId) },
          PropertyItem("Line length",motorDetails.length.toFormatString(),true) { MotorDestinations.UpdateMotorFeedLineLength(motorDetails.relationId)},
          PropertyItem("Method of installation",motorDetails.methodOfInstallation(),true) {
              MotorDestinations.UpdateMotorRelationMethodOdInstallation(motorDetails.relationId)
                                                                                          },
          PropertyItem("Max voltage drop",motorDetails.maxVoltageDrop(),true) {
              MotorDestinations.UpdateMotorRelationMaxVoltDrop(motorDetails.relationId)},
          PropertyItem("Ambient temperature",motorDetails.ambientTemperature(),true) {
              MotorDestinations.UpdateMotorRelationTemperature(motorDetails.relationId, Environment.Ambient)},
          PropertyItem("Ground temperature",motorDetails.groundTemperature(),groundTemperatureVisibility) {
              MotorDestinations.UpdateMotorRelationTemperature(motorDetails.relationId, Environment.Ground)},
          PropertyItem("Soil thermal resistivity",motorDetails.soilThermalResistivity(),groundTemperatureVisibility) {
              MotorDestinations.UpdateMotorRelationSoilResistivity(motorDetails.relationId)
                                                                                                                              },
          PropertyItem("Conductor",motorDetails.conductor(),true) {
              MotorDestinations.UpdateMotorRelationConductor(motorDetails.relationId)
                                                                           },
          PropertyItem("Insulation",motorDetails.insulation(),true) {
              MotorDestinations.UpdateMotorRelationInsulation.invoke(motorDetails.relationId)
          },
          PropertyItem("Circuits in the same conduit",motorDetails.circuitCount.toString(),true) {
              MotorDestinations.UpdateMotorRelationCircuitCount(motorDetails.relationId)
          }
      )
    }

    private fun mapCalculatedFields(motorDetails: MotorDetails):List<Pair<String,String>>{
        return listOf(
            "Current" to motorDetails.current.toFormatString(),
            "Reactive power" to motorDetails.reactivePower.toFormatString(),
            "Apparent power" to motorDetails.apparentPower.toFormatString()
        )
    }
}