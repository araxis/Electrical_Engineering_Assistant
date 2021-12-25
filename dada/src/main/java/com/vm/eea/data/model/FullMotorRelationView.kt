package com.vm.eea.data.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded
import com.vm.eea.application.*
import com.vm.eea.application.protectionDevice.ProtectionType
import com.vm.eea.data.motor.LoadType

@DatabaseView("SELECT full_motor_view.*,\n" +
        "       panel_motor_relations.methodOfInstallation,\n" +
        "       panel_motor_relations.conductor,\n" +
        "       panel_motor_relations.insulation,\n" +
        "       panel_motor_relations.id as relationId,\n" +
        "       panel_motor_relations.length_value,\n" +
        "       panel_motor_relations.length_unit,\n" +
        "       panel_motor_relations.volt_drop_value,\n" +
        "       panel_motor_relations.ambient_temp_value,\n" +
        "       panel_motor_relations.ambient_temp_unit,\n" +
        "       panel_motor_relations.ground_temp_value,\n" +
        "       panel_motor_relations.ground_temp_unit,\n" +
        "       panel_motor_relations.soil_thermal_resistivity_value,\n" +
        "       panel_motor_relations.soil_thermal_resistivity_unit,\n" +
        "       panel_motor_relations.circuit_count_value \n" +
        "  FROM panel_motor_relations\n" +
        "       INNER JOIN\n" +
        "       full_motor_view ON panel_motor_relations.toLoadId = full_motor_view.loadId",viewName = "full_motor_relation_view")
data class FullMotorRelationView(
    val code:String,
    val description:String,
    @Embedded(prefix = "power_")
    val power: Power,
    val cosPhi: Double,
    val demandFactorCosPhi: Double,
    @Embedded(prefix = "efficiency_")
    val efficiency: Efficiency,
    val ratedSpeedRpm:Double,
    val system: PowerSystem,
    val serviceMode: ServiceMode,
    val startMode: StartMode,
    val loadType: LoadType,
    val powerSupplyPath:String,
    val normal:Boolean,
    val emergency:Boolean,
    val projectId:Long,
    val loadId:Long,
    @ColumnInfo(name = "feeder_code")
    val feederCode: String,
    @ColumnInfo(name = "feeder_description")
    val panelDescription: String,
    @ColumnInfo(name = "feeder_is_mdp")
    val isMdp:Boolean,
    @ColumnInfo(name = "feeder_id")
    val panelId: Long,
    val apparentPower:Double,
    val reactivePower:Double,
    val requiredReactivePower:Double,
    val current:Double,
    @ColumnInfo(name = "voltage_volt")
    val voltageInVolt:Double,
    val powerInWatt:Double,
    val applyLocalCosPhiCorrection:Boolean,
    @Embedded(prefix = "length_")
    val length: Length,
    @Embedded(prefix = "volt_drop_")
    val maxVoltageDrop: VoltDrop,
    val methodOfInstallation: MethodOfInstallation,
    @Embedded(prefix = "ambient_temp_")
    val ambientTemperature: Temperature,
    @Embedded(prefix = "ground_temp_")
    val groundTemperature: Temperature,
    @Embedded(prefix = "soil_thermal_resistivity_")
    val soilThermalResistivityUnit: ThermalResistivity,
    @Embedded(prefix = "circuit_count_")
    val circuitCount: CircuitCount,
    val conductor: Conductor,
    val insulation: Insulation,
    val isBiDirect:Boolean,
    val hasOverLoadRelay:Boolean,
    val protectionType: ProtectionType,
    val relationId:Long=0
)