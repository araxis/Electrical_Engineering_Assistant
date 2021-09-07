package com.vm.eea.domain

data class Voltage(val value:Double,val unit: UnitOfVoltage){
  constructor(value:Number, unitOfVoltage: UnitOfVoltage):this(value.toDouble(),unitOfVoltage)
  operator fun invoke()=toString()
  override fun toString(): String {
    return "${value.format()} ${unit.name}"
  }
}

enum class UnitOfVoltage {
  V,KV;

  override fun toString()=name
  operator fun invoke()=toString()
}
