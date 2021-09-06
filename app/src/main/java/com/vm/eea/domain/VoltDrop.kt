package com.vm.eea.domain

import com.vm.eea.utilities.Guard
import com.vm.eea.utilities.format
import com.vm.eea.utilities.outOfRange

class VoltDrop(val value:Double) {
    constructor(value:Number):this(value.toDouble())

    override fun toString() ="${value.format()} %"
    operator fun invoke() =toString()


    init {
       Guard.against.outOfRange(value,1.0,100.0,"powerfactor")
    }
}