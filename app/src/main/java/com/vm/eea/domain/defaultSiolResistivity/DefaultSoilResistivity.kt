package com.vm.eea.domain.defaultSiolResistivity

import com.vm.eea.domain.ThermalResistivity


data class DefaultSoilResistivity(val value:ThermalResistivity,
                           val isCustom:Boolean,
                           val id:Long=0)
