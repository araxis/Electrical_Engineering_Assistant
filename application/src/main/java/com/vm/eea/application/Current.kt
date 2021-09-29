package com.vm.eea.application

data class Current(val value:Double,val unit: Unit){

    constructor(value:Number,unit: Unit):this(value.toDouble(),unit)


    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }

    infix fun to(newUnit: Unit): Current {
        val baseValue=unit.toBase(value)
        val newValue=newUnit.fromBase(baseValue)
        return Current(newValue,newUnit)
    }

    fun toBase(): Current {
        val baseValue= Unit.A.toBase(value)
        return Current(baseValue, Unit.A)
    }

    enum class Unit: IUnit {
        A {
            override fun toBase(value: Double)=value

            override fun fromBase(value: Double)=value
        },KA {
            override fun toBase(value: Double)=value * 1000

            override fun fromBase(value: Double)=value/1000
        };

        operator fun invoke()=this.name
    }
}

