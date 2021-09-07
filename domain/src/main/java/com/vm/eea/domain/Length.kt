package com.vm.eea.domain

data class Length(val value:Double,val unit: UnitOfLength){
    constructor(value:Number,unit: UnitOfLength):this(value.toDouble(),unit)
    override fun toString()="${value.format()} ${unit.name}"
    operator fun invoke()=toString()
}

enum class UnitOfLength {
M,KM;

    operator fun invoke() =name
}
