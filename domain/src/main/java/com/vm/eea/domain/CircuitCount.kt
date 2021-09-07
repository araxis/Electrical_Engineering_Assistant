package com.vm.eea.domain

data class CircuitCount(val value:Int){

    override fun toString()=value.toString()
    operator fun invoke()=toString()
}
