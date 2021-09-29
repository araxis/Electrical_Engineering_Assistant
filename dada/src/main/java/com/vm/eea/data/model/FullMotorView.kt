package com.vm.eea.data.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded
import com.vm.eea.application.*

@DatabaseView("SELECT projectId,\n" +
        "       id,startMode,\n" +
        "       code,\n" +
        "       description,powerInWatt,\n" +
        "       system,\n" +
        "       serviceMode,\n" +
        "       power_value,\n" +
        "       power_unit,\n" +
        "       cosPhi,\n" +
        "       efficiency_value,\n" +
        "       demandFactorCosPhi,applyLocalCosPhiCorrection,\n" +
        "       powerSupplyPath,\n" +
        "       emergency,\n" +
        "       normal,\n" +
        "       loadType,\n" +
        "       voltage_volt,\n" +
        "       powerInWatt * tanPhi AS reactivePower,\n" +
        "       powerInWatt / cosPhi AS apparentPower,\n" +
        "       powerInWatt * (tanPhi - demandFactorTanPhi) AS requiredReactivePower,\n" +
        "       CASE applyLocalCosPhiCorrection WHEN 1 THEN powerInWatt * (tanPhi - demandFactorTanPhi) ELSE  0 END appliedCorrectionVar,\n" +
        "       powerInWatt / (volt_conf * voltage_volt * efficiency_conf * cosPhi) AS [current],\n" +
        "       feeder_id,\n" +
        "       feeder_code,\n" +
        "       feeder_description,\n" +
        "       isMdp AS feeder_is_mdp,\n" +
        "       projectCode,\n" +
        "                   projectDescription,\n" +
        "                   unitOfVoltage,\n" +
        "                   unitOfOfPower,\n" +
        "                   unitOfLength,\n" +
        "                   unitOfTemperature,\n" +
        "                   unitOfWireSize,\n" +
        "                   singlePhaseVoltageInVolt,\n" +
        "                   twoPhaseVoltageInVolt,\n" +
        "                   threePhaseVoltageInVolt,\n" +
        "                   methodOfInstallation,\n" +
        "                   conductor,\n" +
        "                   insulation,\n" +
        "                   standard,\n" +
        "                   isDeleted,\n" +
        "                   id as projectId,\n" +
        "                   altitude_value,\n" +
        "                   altitude_unit,\n" +
        "                   ambient_temp_value,\n" +
        "                   ambient_temp_unit,\n" +
        "                   soil_temp_value,\n" +
        "                   soil_temp_unit,\n" +
        "                   soil_thermal_resist_value,\n" +
        "                   soil_thermal_resist_unit,\n" +
        "                   single_phase_powerfactor_value,\n" +
        "                   two_phase_powerfactor_value,\n" +
        "                   three_phase_powerfactor_value,\n" +
        "                   panel_panel_volt_drop_value,\n" +
        "                   panel_motor_volt_drop_value,\n" +
        "                   circuit_count_value,\n" +
        "                   max_wire_size_value,\n" +
        "                   max_wire_size_unit,\n" +
        "                   min_wire_size_value,\n" +
        "                   min_wire_size_unit\n" +
        "\n" +
        "  FROM (\n" +
        "           SELECT loads.power_value,\n" +
        "                  loads.power_unit,\n" +
        "                  loads.cosPhi,loads.sinPhi,loads.tanPhi,\n" +
        "                  loads.efficiency_value,\n" +
        "                  loads.projectId,\n" +
        "                  loads.demandFactorCosPhi,loads.demandFactorTanPhi,\n" +
        "                  loads.powerSupplyPath,\n" +
        "                  loads.emergency,\n" +
        "                  loads.normal,\n" +
        "                  loads.loadType,\n" +
        "                  loads.code,\n" +
        "                  loads.description,\n" +
        "                  loads.system,\n" +
        "                  loads.serviceMode,\n" +
        "                  loads.startMode,\n" +
        "                  panel_motor_relations.fromPanelId AS feeder_id,\n" +
        "                  panels.code AS feeder_code,\n" +
        "                  panels.description AS feeder_description,\n" +
        "                  panels.isMdp,loads.applyLocalCosPhiCorrection,\n" +
        "                  loads.id,\n" +
        "                  loads.powerInWatt,\n" +
        "                  CASE loads.system WHEN \"SinglePhase\" THEN projects.singlePhaseVoltageInVolt WHEN \"TwoPhase\" THEN projects.twoPhaseVoltageInVolt ELSE threePhaseVoltageInVolt END voltage_volt,\n" +
        "                  CASE loads.system WHEN \"SinglePhase\" THEN 1 WHEN \"TwoPhase\" THEN 1 ELSE 1.732050807 END volt_conf,\n" +
        "                  loads.efficiency_value / 100 AS efficiency_conf,\n" +
        "                   projects.code as projectCode,\n" +
        "                   projects.description as projectDescription,\n" +
        "                   projects.unitOfVoltage,\n" +
        "                   projects.unitOfOfPower,\n" +
        "                   projects.unitOfLength,\n" +
        "                   projects.unitOfTemperature,\n" +
        "                   projects.unitOfWireSize,\n" +
        "                   projects.singlePhaseVoltageInVolt,\n" +
        "                   projects.twoPhaseVoltageInVolt,\n" +
        "                   projects.threePhaseVoltageInVolt,\n" +
        "                   projects.methodOfInstallation,\n" +
        "                   projects.conductor,\n" +
        "                   projects.insulation,\n" +
        "                   projects.standard,\n" +
        "                   projects.isDeleted,\n" +
        "                   projects.id as projectId,\n" +
        "                   projects.altitude_value,\n" +
        "                   projects.altitude_unit,\n" +
        "                   projects.ambient_temp_value,\n" +
        "                   projects.ambient_temp_unit,\n" +
        "                   projects.soil_temp_value,\n" +
        "                   projects.soil_temp_unit,\n" +
        "                   projects.soil_thermal_resist_value,\n" +
        "                   projects.soil_thermal_resist_unit,\n" +
        "                   projects.single_phase_powerfactor_value,\n" +
        "                   projects.two_phase_powerfactor_value,\n" +
        "                   projects.three_phase_powerfactor_value,\n" +
        "                   projects.panel_panel_volt_drop_value,\n" +
        "                   projects.panel_motor_volt_drop_value,\n" +
        "                   projects.circuit_count_value,\n" +
        "                   projects.max_wire_size_value,\n" +
        "                   projects.max_wire_size_unit,\n" +
        "                   projects.min_wire_size_value,\n" +
        "                   projects.min_wire_size_unit\n" +
        "             FROM loads\n" +
        "                  INNER JOIN\n" +
        "                  projects ON loads.projectId = projects.id\n" +
        "                  INNER JOIN\n" +
        "                  panel_motor_relations ON loads.id = panel_motor_relations.toLoadId\n" +
        "                  INNER JOIN\n" +
        "                  panels ON projects.id = panels.projectId AND \n" +
        "                            panel_motor_relations.fromPanelId = panels.id\n" +
        "       )",viewName = "full_motor_view")
data class FullMotorView(
    val code:String,
    val description:String,
    @Embedded(prefix = "power_")
    val power: Power,
    val cosPhi: Double,
    val demandFactorCosPhi: Double,
    @Embedded(prefix = "efficiency_")
    val efficiency: Efficiency,
    val system: PowerSystem,
    val serviceMode: ServiceMode,
    val startMode: StartMode,
    val loadType: LoadType,
    val powerSupplyPath:String,
    val normal:Boolean,
    val emergency:Boolean,
    val projectId:Long,
    val id:Long,
    @ColumnInfo(name = "feeder_code")
    val panelCode: String,
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
    val projectCode:String,
    val projectDescription: String,
    val unitOfVoltage: Voltage.Unit,
    val unitOfOfPower: Power.Unit,
    val unitOfLength: Length.Unit,
    val unitOfTemperature: UnitOfTemperature,
    val unitOfWireSize: UnitOfWireSize,
    val singlePhaseVoltageInVolt: Double,
    val twoPhaseVoltageInVolt: Double,
    val threePhaseVoltageInVolt: Double,
    @Embedded(prefix = "altitude_")
    val altitude: Length,
    val methodOfInstallation: MethodOfInstallation,
    @Embedded(prefix = "ambient_temp_")
    val ambientTemperature: Temperature,
    @Embedded(prefix = "soil_temp_")
    val groundTemperature: Temperature,
    @Embedded(prefix = "soil_thermal_resist_")
    val soilResistivity: ThermalResistivity,
    val conductor: Conductor,
    val insulation: Insulation,
    @Embedded(prefix = "single_phase_powerfactor_")
    val singlePhaseCosPhi: CosPhi,
    @Embedded(prefix = "two_phase_powerfactor_")
    val twoPhaseCosPhi: CosPhi,
    @Embedded(prefix = "three_phase_powerfactor_")
    val threePhaseCosPhi: CosPhi,
    @Embedded(prefix = "panel_panel_volt_drop_")
    val panelToPanelMaxVoltDrop: VoltDrop,
    @Embedded(prefix = "panel_motor_volt_drop_")
    val panelToMotorMaxVoltDrop: VoltDrop,
    @Embedded(prefix = "circuit_count_")
    val circuitInTheSameConduit: CircuitCount,
    @Embedded(prefix = "max_wire_size_")
    val maxWireSize: WireSize,
    @Embedded(prefix = "min_wire_size_")
    val minWireSize: WireSize,
    val standard: Standard,
    val isDeleted:Boolean=false
    )
