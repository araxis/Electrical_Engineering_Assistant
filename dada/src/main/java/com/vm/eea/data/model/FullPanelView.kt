package com.vm.eea.data.model

import androidx.room.DatabaseView
import androidx.room.Embedded
import com.vm.eea.application.*

@DatabaseView("SELECT panels.id,\n" +
        "       panels.projectId, \n" +
        "       panels.code,\n" +
        "       panels.description,\n" +
        "       panels.isMdp,\n" +
        "       panels.demand_factor_value,\n" +
        "       panels.powerSupplyPath,\n" +
        "       panels.coincidenceFactor,\n" +
        "       panels1.id as feederId,      \n" +
        "       panels1.code as feederCode,\n" +
        "       panels1.description as feederDescription,\n" +
        "       panels1.isMdp as feederIsMdp,\n" +
        "       projects.code as projectCode,\n" +
        "       projects.description as projectDescription,\n" +
        "       projects.lineToNullVoltage as projectLineToNullVoltageInVolt,\n" +
        "       projects.lineToLineVoltage as projectLineToLineVoltageInVolt,\n" +
        "       projects.methodOfInstallation as projectMethodOfInstallation,\n" +
        "       projects.conductor as projectConductor,\n" +
        "       projects.insulation as projectInsulation,\n" +
        "       projects.standard as projectStandard,\n" +
        "       projects.isDeleted,\n" +
        "       projects.altitude_value as project_altitude_value,\n" +
        "       projects.altitude_unit as project_altitude_unit,\n" +
        "       projects.ambient_temp_value as project_ambient_temp_value,\n" +
        "       projects.ambient_temp_unit as project_ambient_temp_unit,\n" +
        "       projects.soil_temp_value as project_soil_temp_value,\n" +
        "       projects.soil_temp_unit as project_soil_temp_unit,\n" +
        "       projects.soil_thermal_resist_value as project_soil_thermal_resist_value,\n" +
        "       projects.soil_thermal_resist_unit as project_soil_thermal_resist_unit,\n" +
        "       projects.panel_panel_volt_drop_value as project_panel_panel_volt_drop_value,\n" +
        "       projects.panel_motor_volt_drop_value as project_panel_motor_volt_drop_value,\n" +
        "       projects.circuit_count_value as project_circuit_count_value,\n" +
        "       projects.max_wire_size_value as project_max_wire_size_value,\n" +
        "       projects.max_wire_size_unit as project_max_wire_size_unit,\n" +
        "       projects.min_wire_size_value as project_min_wire_size_value,\n" +
        "       projects.min_wire_size_unit as project_min_wire_size_unit,\n" +
        "       IFNULL(sum(full_motor_view.[current]), 0) AS totalCurrent,\n" +
        "       IFNULL(sum(full_motor_view.apparentPower), 0) AS totalApparentPower,\n" +
        "       IFNULL(sum(appliedCorrectionVar), 0) AS appliedCorrectionVar,\n" +
        "       IFNULL(sum(full_motor_view.reactivePower), 0) AS totalReactivePower,\n" +
        "       IFNULL(sum(full_motor_view.powerInWatt), 0) AS totalActivePower\n" +
        "  FROM panels\n" +
        "        inner join projects on panels.projectId=projects.id\n" +
        "        LEFT JOIN panel_panel_relations ON panels.id = panel_panel_relations.toPanelId\n" +
        "        LEFT JOIN panels as panels1 ON panel_panel_relations.fromPanelId = panels1.id\n" +
        "        LEFT JOIN full_motor_view ON full_motor_view.projectId = panels.projectId and full_motor_view.powerSupplyPath LIKE panels.powerSupplyPath || '%'\n" +
        "        group by panels.id",viewName = "full_panel_view")
data class FullPanelView(val projectId: Long,
                         val code: String,
                         val description:String,
                         val isMdp:Boolean,
                         @Embedded(prefix = "demand_factor_")
                          val demandFactor: CosPhi,
                         val powerSupplyPath:String,
                         val id: Long,
                         val feederId:Long?,
                         val feederCode:String?,
                         val feederDescription:String?,
                         val feederIsMdp:Boolean?,
                         val totalCurrent:Double,
                         val coincidenceFactor:Double,
                         val totalApparentPower:Double,
                         val totalReactivePower: Double,
                         val totalActivePower:Double,
                         val appliedCorrectionVar:Double,
                         val projectCode:String,
                         val projectDescription: String,
                         val projectLineToNullVoltageInVolt: Double,
                         val projectLineToLineVoltageInVolt: Double,
                         @Embedded(prefix = "project_altitude_")
                         val altitude: Length,
                         val projectMethodOfInstallation: MethodOfInstallation,
                         @Embedded(prefix = "project_ambient_temp_")
                         val ambientTemperature: Temperature,
                         @Embedded(prefix = "project_soil_temp_")
                         val groundTemperature: Temperature,
                         @Embedded(prefix = "project_soil_thermal_resist_")
                         val soilResistivity: ThermalResistivity,
                         val projectConductor: Conductor,
                         val projectInsulation: Insulation,
                         @Embedded(prefix = "project_panel_panel_volt_drop_")
                         val panelToPanelMaxVoltDrop: VoltDrop,
                         @Embedded(prefix = "project_panel_motor_volt_drop_")
                         val panelToMotorMaxVoltDrop: VoltDrop,
                         @Embedded(prefix = "project_circuit_count_")
                         val circuitInTheSameConduit: CircuitCount,
                         @Embedded(prefix = "project_max_wire_size_")
                         val maxWireSize: WireSize,
                         @Embedded(prefix = "project_min_wire_size_")
                         val minWireSize: WireSize,
                         val projectStandard: Standard,
                         val isDeleted:Boolean=false

)