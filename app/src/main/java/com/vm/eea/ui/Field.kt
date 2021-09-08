package com.vm.eea.ui

import com.vm.eea.domain.*
import com.vm.eea.utilities.IText
typealias PowerField=Field<UnitOfPower>
typealias VoltageField=Field<UnitOfVoltage>
typealias VoltDropField=StringField
typealias CircuitCountField=StringField
typealias PowerfactorField=Field<String>
typealias LengthField=Field<UnitOfLength>
typealias TemperatureField=Field<UnitOfTemperature>
typealias ThermalResistivityField=Field<UnitOfThermalResistivity>
typealias EfficiencyField=Field<String>
typealias StringField=Field<String>

typealias StringListener=(String)->Unit
typealias PowerListener =(String, UnitOfPower)->Unit
typealias VoltageListener =(String, UnitOfVoltage)->Unit
typealias EfficiencyListener = StringListener
typealias PowerfactorListener =StringListener
typealias VoltDropListener =StringListener
typealias LengthListener =(String, UnitOfLength)->Unit


data class Field<T>(val value:String, val second:T, val error: IText?){

    companion object{
        fun empty()=StringField("","",null)
        fun<T> empty(second:T)=Field("",second,null)

    }
}






