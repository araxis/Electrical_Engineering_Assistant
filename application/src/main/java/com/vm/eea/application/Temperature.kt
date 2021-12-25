package com.vm.eea.application

data class Temperature(override val value:Double, override val unit: UnitOfTemperature):IQuantity<UnitOfTemperature>{

    constructor(value:Number, unit: UnitOfTemperature):this(value.toDouble(),unit)
    override fun toString()="${value.format()} ${unit.name}"
    operator fun invoke()=toString()

    fun toString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }
}

enum class UnitOfTemperature:IUnit {
    C {
        override val symbol ="c"
        override fun toBase(value: Double): Double {
            TODO("Not yet implemented")
        }

        override fun fromBase(value: Double): Double {
            TODO("Not yet implemented")
        }
    },F {
        override val symbol ="f"
        override fun toBase(value: Double): Double {
            TODO("Not yet implemented")
        }

        override fun fromBase(value: Double): Double {
            TODO("Not yet implemented")
        }
    };

    operator fun invoke() =name

}
