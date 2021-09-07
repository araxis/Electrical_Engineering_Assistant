package com.vm.eea.domain.defaultGroundTemperature

import com.vm.eea.domain.Environment
import com.vm.eea.domain.Temperature


data class DefaultTemperature(val value: Temperature,
                              val isCustom:Boolean,
                              val environment: Environment,
                              val id:Long=0)
