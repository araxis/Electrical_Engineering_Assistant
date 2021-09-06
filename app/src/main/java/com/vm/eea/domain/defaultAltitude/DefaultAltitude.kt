package com.vm.eea.domain.defaultAltitude

import com.vm.eea.domain.Length


data class DefaultAltitude(val value:Length,
                           val isCustom:Boolean,
                           val id:Long=0)
