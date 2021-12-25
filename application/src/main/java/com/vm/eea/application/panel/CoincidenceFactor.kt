package com.vm.eea.application.panel


data class CoincidenceFactor(val value:Double) {
    constructor(value:Number):this(value.toDouble())
    init {
        require(value in .1..1.0){"value must be .1..1"}
    }
}