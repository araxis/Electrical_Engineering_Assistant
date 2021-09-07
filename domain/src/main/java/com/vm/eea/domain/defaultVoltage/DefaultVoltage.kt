package com.vm.eea.domain.defaultVoltage

import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.Voltage

data class DefaultVoltage(val voltage: Voltage,
                          val system: PowerSystem,
                          val isCustom:Boolean,
                          val id:Long=0)
