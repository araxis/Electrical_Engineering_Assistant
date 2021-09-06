package com.vm.eea.domain.defaultFeedingConfig

import com.vm.eea.domain.*
import com.vm.eea.utilities.c
import com.vm.eea.utilities.mm2
import com.vm.eea.utilities.voltDrop

data class FeedingConfig(
    val methodOfInstallation: MethodOfInstallation,
    val maxVoltDrop: VoltDrop,
    val conductor: Conductor,
    val ambientTemperature: Temperature,
    val groundTemperature: Temperature,
    val soilThermalResistivity: ThermalResistivity,
    val insulation: Insulation,
    val circuitCount: CircuitCount,
    val maxWireSize: WireSize
){
    companion object{
        fun empty()=FeedingConfig(MethodOfInstallation.A1,maxVoltDrop = 4.voltDrop,Conductor.Copper,
        25.c,25.c,
            ThermalResistivity(2.5,UnitOfThermalResistivity.MW),Insulation.PVC,
            CircuitCount(1),240.mm2
        )
    }
}
