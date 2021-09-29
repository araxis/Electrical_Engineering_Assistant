package com.vm.eea.application

data class Length(val value:Double,val unit: Unit){
    constructor(value:Number,unit: Unit):this(value.toDouble(),unit)


    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }

    enum class Unit{
        M,KM;

        operator fun invoke() =name
    }

}


