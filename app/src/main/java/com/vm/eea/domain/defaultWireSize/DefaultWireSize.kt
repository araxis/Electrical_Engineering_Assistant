package com.vm.eea.domain.defaultWireSize

import com.vm.eea.domain.WireSize

data class DefaultWireSize(val wireSize: WireSize, val isCustom:Boolean, val id:Long=0) {
}