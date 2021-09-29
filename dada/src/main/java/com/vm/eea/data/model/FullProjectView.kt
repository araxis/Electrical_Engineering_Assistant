package com.vm.eea.data.model

import androidx.room.DatabaseView
import androidx.room.Embedded
import com.vm.eea.application.*

@DatabaseView("select projects.id,projects.code,\n" +
        "       projects.description,\n" +
        "       projects.unitOfVoltage,\n" +
        "       projects.unitOfOfPower,\n" +
        "       projects.unitOfLength,\n" +
        "       projects.unitOfTemperature,\n" +
        "       projects.unitOfWireSize,\n" +
        "       projects.singlePhaseVoltageInVolt,\n" +
        "       projects.twoPhaseVoltageInVolt,\n" +
        "       projects.threePhaseVoltageInVolt,\n" +
        "       projects.methodOfInstallation,\n" +
        "       projects.conductor,\n" +
        "       projects.insulation,\n" +
        "       projects.standard,\n" +
        "       projects.altitude_value,\n" +
        "       projects.altitude_unit,\n" +
        "       projects.ambient_temp_value,\n" +
        "       projects.ambient_temp_unit,\n" +
        "       projects.soil_temp_value,\n" +
        "       projects.soil_temp_unit,\n" +
        "       projects.soil_thermal_resist_value,\n" +
        "       projects.soil_thermal_resist_unit,\n" +
        "       projects.single_phase_powerfactor_value,\n" +
        "       projects.two_phase_powerfactor_value,\n" +
        "       projects.three_phase_powerfactor_value,\n" +
        "       projects.panel_panel_volt_drop_value,\n" +
        "       projects.panel_motor_volt_drop_value,\n" +
        "       projects.circuit_count_value,\n" +
        "       projects.max_wire_size_value,\n" +
        "       projects.max_wire_size_unit,\n" +
        "       projects.min_wire_size_value,\n" +
        "       projects.min_wire_size_unit,\n" +
        "       full_panel_view.totalCurrent,\n" +
        "       full_panel_view.totalApparentPower,\n" +
        "       full_panel_view.totalReactivePower,\n" +
        "       full_panel_view.totalActivePower\n" +
        "       from projects\n" +
        "   \n" +
        "       inner join full_panel_view on  full_panel_view.projectId=projects.id and full_panel_view.isMdp=1\n" +
        "           where projects.isDeleted=0",viewName = "full_project_view")
data class FullProjectView(val code:String,
                           val description: String,
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
                           val id: Long,
                           val totalCurrent:Double,
                           val totalApparentPower:Double,
                           val totalReactivePower: Double,
                           val totalActivePower:Double) {
}