package com.vm.eea.data.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded
import com.vm.eea.application.*
import com.vm.eea.application.protectionDevice.ProtectionType
import com.vm.eea.data.motor.LoadType

@DatabaseView("SELECT projectId,\n" +
        "       loadId,\n" +
        "       startMode,\n" +
        "       isBiDirect,\n" +
        "       hasOverLoadRelay,\n" +
        "       protectionType,\n" +
        "       code,\n" +
        "       description,\n" +
        "       powerInWatt,\n" +
        "       system,\n" +
        "       serviceMode,\n" +
        "       power_value,\n" +
        "       power_unit,\n" +
        "       cosPhi,\n" +
        "       efficiency_value,\n" +
        "       ratedSpeedRpm,\n" +
        "       demandFactorCosPhi,\n" +
        "       applyLocalCosPhiCorrection,\n" +
        "       powerSupplyPath,\n" +
        "       emergency,\n" +
        "       normal,\n" +
        "       loadType,\n" +
        "       voltage_volt,\n" +
        "       powerInWatt * tanPhi AS reactivePower,\n" +
        "       powerInWatt / cosPhi AS apparentPower,\n" +
        "       powerInWatt * (tanPhi - demandFactorTanPhi) AS requiredReactivePower,\n" +
        "       CASE applyLocalCosPhiCorrection WHEN 1 THEN powerInWatt * (tanPhi - demandFactorTanPhi) ELSE 0 END appliedCorrectionVar,\n" +
        "       powerInWatt / (volt_conf * voltage_volt * efficiency_conf * cosPhi) AS [current],\n" +
        "       feeder_id,\n" +
        "       feeder_code,\n" +
        "       feeder_description,\n" +
        "       isMdp AS feeder_is_mdp,\n" +
        "       projectCode,\n" +
        "       projectDescription,\n" +
        "     \n" +
        "       isDeleted\n" +
        "\n" +
        "  FROM (\n" +
        "           SELECT loads.power_value,\n" +
        "                  loads.power_unit,\n" +
        "                  loads.cosPhi,\n" +
        "                  loads.sinPhi,\n" +
        "                  loads.tanPhi,\n" +
        "                  loads.efficiency_value,\n" +
        "                  loads.ratedSpeedRpm,\n" +
        "                  loads.projectId,\n" +
        "                  loads.demandFactorCosPhi,\n" +
        "                  loads.demandFactorTanPhi,\n" +
        "                  loads.powerSupplyPath,\n" +
        "                  loads.emergency,\n" +
        "                  loads.normal,\n" +
        "                  loads.loadType,\n" +
        "                  loads.code,\n" +
        "                  loads.description,\n" +
        "                  loads.system,\n" +
        "                  loads.serviceMode,\n" +
        "                  loads.startMode,\n" +
        "                  loads.isBiDirect,\n" +
        "                  loads.hasOverLoadRelay,\n" +
        "                  loads.protectionType,\n" +
        "                  panel_motor_relations.fromPanelId AS feeder_id,\n" +
        "                  panels.code AS feeder_code,\n" +
        "                  panels.description AS feeder_description,\n" +
        "                  panels.isMdp,\n" +
        "                  loads.applyLocalCosPhiCorrection,\n" +
        "                  loads.id as loadId,\n" +
        "                  loads.powerInWatt,\n" +
        "                  CASE loads.system WHEN \"SinglePhase\" THEN projects.lineToNullVoltage WHEN \"TwoPhase\" THEN projects.lineToLineVoltage ELSE lineToLineVoltage END voltage_volt,\n" +
        "                  CASE loads.system WHEN \"SinglePhase\" THEN 1 WHEN \"TwoPhase\" THEN 1 ELSE 1.732050807 END volt_conf,\n" +
        "                  loads.efficiency_value / 100 AS efficiency_conf,\n" +
        "                  projects.code AS projectCode,\n" +
        "                  projects.description AS projectDescription,\n" +
        "                  projects.isDeleted,\n" +
        "                  projects.id AS projectId\n" +
        "             FROM loads\n" +
        "                  INNER JOIN\n" +
        "                  projects ON loads.projectId = projects.id\n" +
        "                  INNER JOIN\n" +
        "                  panel_motor_relations ON loads.id = panel_motor_relations.toLoadId\n" +
        "                  INNER JOIN\n" +
        "                  panels ON projects.id = panels.projectId AND panel_motor_relations.fromPanelId = panels.id\n" +
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
    val ratedSpeedRpm: Double,
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
    val isDeleted:Boolean,
    val isBiDirect:Boolean,
    val hasOverLoadRelay:Boolean,
    val protectionType: ProtectionType


    )
