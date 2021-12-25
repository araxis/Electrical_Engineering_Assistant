package com.vm.eea.application

data class ThermalResistivity(override val value:Double, override val unit: UnitOfThermalResistivity):IQuantity<UnitOfThermalResistivity>{
    constructor(value:Number, unitOfThermalResistivity: UnitOfThermalResistivity):this(value.toDouble(),unitOfThermalResistivity)

    override fun toString()="${value.format()} ${unit.name}"
    operator fun invoke() =toString()

    fun toString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }
}

enum class UnitOfThermalResistivity:IUnit {
MW {
    override val symbol ="mw"
    override fun toBase(value: Double): Double {
        TODO("Not yet implemented")
    }

    override fun fromBase(value: Double): Double {
        TODO("Not yet implemented")
    }
};
    operator fun invoke()=name

}
