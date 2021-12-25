package com.vm.eea.application

import kotlin.math.pow

data class Voltage(override val value:Double, override val unit: Unit):IQuantity<Voltage.Unit>{

  constructor(value:Number, unitOfVoltage: Unit):this(value.toDouble(),unitOfVoltage)

  operator fun invoke()=toString()
  override fun toString(): String {
    return "${value.format()} ${unit.name}"
  }

  fun toFormatString(pattern:String="###.###",empty:String=""):String{
    return "${value.format(pattern,empty)} ${unit.name}"
  }

  operator fun times(conf:Number)= Voltage(conf.toDouble()*value,unit)
  operator fun times(cosPhi: CosPhi)= Voltage(cosPhi.value*value,unit)
  operator fun times(efficiency: Efficiency)= Voltage(efficiency.value*value/100,unit)

  fun pow2()=this.copy(value= value.pow(2.0),unit=unit)

  infix fun to(newUnit: Unit): Voltage {
    val baseValue=unit.toBase(value)
    val newValue=newUnit.fromBase(baseValue)
    return Voltage(newValue,newUnit)
  }

  operator fun times(current: Current): Power {
    val baseVoltage= to(Unit.V)
    val baseCurrent=current.toBase()
    return Power.toBase(baseVoltage.value * baseCurrent.value)
  }






    enum class Unit: IUnit {
    V {
      override val symbol ="v"
      override fun toBase(value: Double)=value

      override fun fromBase(value: Double)=value
    },KV {
        override val symbol ="kv"
      override fun toBase(value: Double)=value *1000

      override fun fromBase(value: Double) =value/1000
    };

    override fun toString()=name
    operator fun invoke()=toString()
  }
}


