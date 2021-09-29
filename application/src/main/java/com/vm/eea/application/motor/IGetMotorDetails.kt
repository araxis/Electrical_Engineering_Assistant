package com.vm.eea.application.motor

import com.vm.eea.application.*
import kotlinx.coroutines.flow.Flow

interface IGetMotorDetails {

    operator fun invoke(motorId: MotorId): Flow<MotorDetails>
}

data class MotorDetails(val code:String,
                        val description:String,
                        val power: Power,
                        val cosPhi: CosPhi,
                        val demandFactor: CosPhi,
                        val efficiency: Efficiency,
                        val system: PowerSystem,
                        val serviceMode: ServiceMode,
                        val startMode: StartMode,
                        val feedingMode: FeedingMode,
                        val feederCode: String,
                        val feederId: PanelId,
                        val reactivePower:ReactivePower,
                        val apparentPower:ApparentPower,
                        val current:Current,
                        val voltage:Voltage,
                        val length: Length,
                        val maxVoltageDrop: VoltDrop,
                        val methodOfInstallation: MethodOfInstallation,
                        val ambientTemperature: Temperature,
                        val groundTemperature: Temperature,
                        val soilThermalResistivity: ThermalResistivity,
                        val conductor: Conductor,
                        val insulation: Insulation,
                        val circuitCount: CircuitCount,
                        val relationId: RelationId )