package com.vm.eea.ui

import com.vm.eea.domain.UnitOfLength
import com.vm.eea.domain.UnitOfPower
import com.vm.eea.domain.UnitOfVoltage
import com.vm.eea.utilities.IText
typealias PowerField=Field<UnitOfPower>
typealias VoltageField=Field<UnitOfVoltage>
typealias PowerfactorField=Field<String>
typealias LengthField=Field<UnitOfLength>
typealias EfficiencyField=Field<String>
typealias StringField=Field<String>

typealias StringListener=(String)->Unit
typealias PowerListener =(String, UnitOfPower)->Unit
typealias VoltageListener =(String, UnitOfVoltage)->Unit
typealias EfficiencyListener = StringListener
typealias PowerfactorListener =StringListener
typealias LengthListener =(String, UnitOfLength)->Unit


data class Field<T>(val value:String, val second:T, val error: IText?){

    companion object{
        fun empty()=StringField("","",null)
        fun<T> empty(second:T)=Field("",second,null)

    }
}






