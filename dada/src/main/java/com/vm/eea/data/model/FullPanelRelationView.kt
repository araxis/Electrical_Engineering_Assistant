package com.vm.eea.data.model

import androidx.room.DatabaseView
import androidx.room.Embedded
import com.vm.eea.application.*

@DatabaseView("SELECT full_panel_view.*,\n" +
        "panel_panel_relations.methodOfInstallation,\n" +
        "       panel_panel_relations.conductor,\n" +
        "       panel_panel_relations.insulation,\n" +
        "       panel_panel_relations.id as relationId,\n" +
        "       panel_panel_relations.length_value,\n" +
        "       panel_panel_relations.length_unit,\n" +
        "       panel_panel_relations.volt_drop_value,\n" +
        "       panel_panel_relations.ambient_temp_value,\n" +
        "       panel_panel_relations.ambient_temp_unit,\n" +
        "       panel_panel_relations.ground_temp_value,\n" +
        "       panel_panel_relations.ground_temp_unit,\n" +
        "       panel_panel_relations.soil_thermal_resistivity_value,\n" +
        "       panel_panel_relations.soil_thermal_resistivity_unit,\n" +
        "       panel_panel_relations.circuit_count_value \n" +
        "       \n" +
        "  FROM full_panel_view\n" +
        "       Left JOIN\n" +
        "       panel_panel_relations ON panel_panel_relations.toPanelId = full_panel_view.id",viewName = "full_panel_relation_view")
data class FullPanelRelationView(val projectId: Long,
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
                                 @Embedded(prefix = "length_")
                                 val length: Length?,
                                 @Embedded(prefix = "volt_drop_")
                                 val maxVoltageDrop: VoltDrop?,
                                 val methodOfInstallation: MethodOfInstallation?,
                                 @Embedded(prefix = "ambient_temp_")
                                 val ambientTemperature: Temperature?,
                                 @Embedded(prefix = "ground_temp_")
                                 val groundTemperature: Temperature?,
                                 @Embedded(prefix = "soil_thermal_resistivity_")
                                 val soilThermalResistivityUnit: ThermalResistivity?,
                                 @Embedded(prefix = "circuit_count_")
                                 val circuitCount: CircuitCount?,
                                 val conductor: Conductor?,
                                 val insulation: Insulation?,
                                 val relationId:Long=0)
