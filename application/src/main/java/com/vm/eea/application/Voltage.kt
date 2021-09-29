package com.vm.eea.application

data class Voltage(val value:Double,val unit: Unit){

  constructor(value:Number, unitOfVoltage: Unit):this(value.toDouble(),unitOfVoltage)

  operator fun invoke()=toString()
  override fun toString(): String {
    return "${value.format()} ${unit.name}"
  }

  fun toString(pattern:String="###.###",empty:String=""):String{
    return "${value.format(pattern,empty)} ${unit.name}"
  }

  operator fun times(conf:Number)= Voltage(conf.toDouble()*value,unit)
  operator fun times(cosPhi: CosPhi)= Voltage(cosPhi.value*value,unit)
  operator fun times(efficiency: Efficiency)= Voltage(efficiency.value*value/100,unit)

  operator fun times(current: Current): Power {
    val baseVoltage= toBase()
    val baseCurrent=current.toBase()
    return Power.toBase(baseVoltage.value * baseCurrent.value)
  }


  fun toBase(): Voltage {
    val baseValue= Unit.V.toBase(value)
    return Voltage(baseValue, Unit.V)
  }



  infix fun to(newUnit: Unit): Voltage {
    val baseValue=unit.toBase(value)
    val newValue=newUnit.fromBase(baseValue)
    return Voltage(newValue,newUnit)
  }

  enum class Unit: IUnit {
    V {
      override fun toBase(value: Double)=value

      override fun fromBase(value: Double)=value
    },KV {
      override fun toBase(value: Double)=value *1000

      override fun fromBase(value: Double) =value/1000
    };

    override fun toString()=name
    operator fun invoke()=toString()
  }
}


