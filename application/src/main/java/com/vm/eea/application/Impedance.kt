package com.vm.eea.application

data class Impedance(override val value:Double, override val unit:Unit):IQuantity<Impedance.Unit>{
   constructor(value:Number,unit:Unit):this(value.toDouble(),unit)

    infix fun to(newUnit: Unit): Impedance {
        val baseValue=unit.toBase(value)
        val newValue=newUnit.fromBase(baseValue)
        return Impedance(newValue,newUnit)
    }
    enum class Unit: IUnit {
        Ohm {
            override val symbol ="o"
            override fun toBase(value: Double)=value

            override fun fromBase(value: Double)=value
        },KOhm {
            override val symbol ="ko"
            override fun toBase(value: Double)=value *1000

            override fun fromBase(value: Double) =value/1000
        };

        override fun toString()=name
        operator fun invoke()=toString()
    }
}
