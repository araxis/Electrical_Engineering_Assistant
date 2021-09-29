package com.vm.eea.ui

import com.vm.eea.application.*
import com.vm.eea.utilities.IText

typealias VoltageField=Field<Voltage.Unit>
typealias VoltDropField=StringField
typealias CircuitCountField=StringField
typealias DemandFactorField=Field<String>
typealias TemperatureField=Field<UnitOfTemperature>
typealias ThermalResistivityField=Field<UnitOfThermalResistivity>
typealias EfficiencyField=Field<String>


typealias StringListener=(String)->Unit
typealias PowerListener =(String, Power.Unit)->Unit
typealias VoltageListener =(String, Voltage.Unit)->Unit
typealias EfficiencyListener = StringListener
typealias PowerfactorListener =StringListener
typealias VoltDropListener =StringListener
typealias LengthListener =(String, Length.Unit)->Unit




data class PowerField(
    val value: String,
    val second: Power.Unit,
    val error: IText?

){
    companion object{
        fun empty(unit:Power.Unit=Power.Unit.W)=PowerField("",unit,null)
    }
}

data class LengthField(
    val value: String,
    val second: Length.Unit,
    val error: IText?

){
    companion object{
        fun empty(unit:Length.Unit=Length.Unit.M)=LengthField("",unit,null)
    }
}

data class StringField(val value: String,
                       val second: String,
                       val error: IText?){
 companion object{
     fun empty(suffix:String="")=StringField("",suffix,null)
 }
}



data class Field<T>(val value:String, val second:T,  val error: IText?){

    companion object{
        fun empty()=StringField("","",null)
        fun<T> empty(second:T)=Field("",second,null)

    }
}






