package com.vm.eea.ui

import com.vm.eea.application.*
import com.vm.eea.application.panel.CoincidenceFactor
import com.vm.eea.application.protectionDevice.ProtectionDeviceType
import com.vm.eea.application.protectionDevice.ProtectionType
import com.vm.eea.utilities.*


typealias StringListener=(String)->Unit
typealias PowerValidator=(String,Power.Unit)->IText?
typealias ApparentPowerValidator=(String,ApparentPower.Unit)->IText?
typealias ReactivePowerValidator=(String, ReactivePower.Unit)->IText?
typealias CurrentValidator=(String,Current.Unit)->IText?



data class PowerField(
    val label: String,
    val input: String,
    val unit: Power.Unit,
    val isValid:Boolean,
    val error: IText?,
    val isVisible:Boolean=true

){
    val value get() =  input be unit
    val notValid=!isValid

    fun validate(value: String,unit: Power.Unit,validator:PowerValidator={s,_->Validator.validate.positiveNumber(s,"")}):PowerField{
        val validationResult= validator(value,unit)
        return copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
    }
    companion object{

        fun empty(label: String="Power",unit:Power.Unit=Power.Unit.KW)=PowerField(label,"",unit,false,null)
        fun valid(label: String="Power",value:Power)=PowerField(label,value.value.format(),value.unit,true,null)
    }
}


data class ApparentPowerField(
    val value: String,
    val second: ApparentPower.Unit,
    val isValid:Boolean,
    val error: IText?,
    val isVisible:Boolean=true

){
    val apparentPower get() =  value be second
    val notValid=!isValid

    fun validate(value: String,unit: ApparentPower.Unit,validator:ApparentPowerValidator={s,_->Validator.validate.positiveNumber(s,"")}):ApparentPowerField{
        val validationResult= validator(value,unit)
        return copy(value=value,second = unit,isValid = validationResult==null,error = validationResult)
    }
    companion object{
        fun empty(unit:ApparentPower.Unit=ApparentPower.Unit.VA)=ApparentPowerField("",unit,false,null)
    }
}

data class StartModeField(val label:String,val value:StartMode,val isValid:Boolean,val error: IText?,val isVisible:Boolean=true){
    val notValid=!isValid
    companion object{
        fun empty(label: String="Start mode",value: StartMode=StartMode.Dol)=StartModeField(label,value,true,null)
    }
}

data class PowerSystemField(val label:String,val value:PowerSystem,val isValid:Boolean,val error: IText?,val isVisible:Boolean=true){
    val notValid=!isValid
    companion object{
        fun empty(label: String="Current type",value: PowerSystem=PowerSystem.ThreePhase)=PowerSystemField(label,value,true,null)
    }
}

data class ProtectionTypeField(val label:String,val value:ProtectionType,val isValid:Boolean,val error: IText?,val isVisible:Boolean=true){
    companion object{
        fun empty(label: String,value: ProtectionType=ProtectionType.CircuitBreaker)=ProtectionTypeField(label,value,true,null)
    }
}

data class KeyTypeField(val label:String,val value:ProtectionDeviceType,val isValid:Boolean,val error: IText?,val isVisible:Boolean=true){
    companion object{
        fun empty(label: String,value: ProtectionDeviceType=ProtectionDeviceType.Tmb)=KeyTypeField(label,value,true,null)
    }
}



data class ReactivePowerField(
    val input: String,
    val unit: ReactivePower.Unit,
    val isValid:Boolean,
    val error: IText?,
    val isVisible:Boolean=true

){
    val value get() =  input be unit
    val notValid=!isValid

    fun validate(value: String,unit: ReactivePower.Unit,validator:ReactivePowerValidator={ s, _->Validator.validate.positiveNumber(s,"")}):ReactivePowerField{
        val validationResult= validator(value,unit)
        return copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
    }

    companion object{
        fun empty(unit:ReactivePower.Unit=ReactivePower.Unit.VAr)=ReactivePowerField("",unit,false,null)
    }
}

data class ResistanceField(
    val value: String,
    val second: Resistance.Unit,
    val isValid:Boolean,
    val error: IText?,
    val isVisible:Boolean=true

){
    val resistance get() =  value be second
    val notValid=!isValid
    companion object{
        fun empty(unit:Resistance.Unit=Resistance.Unit.Ohm)=ResistanceField("",unit,false,null)
    }
}

data class ImpedanceField(
    val value: String,
    val second: Impedance.Unit,
    val isValid:Boolean,
    val error: IText?,
    val isVisible:Boolean=true

){
    val impedance get() =  value be second
    val notValid=!isValid
    companion object{
        fun empty(unit:Impedance.Unit=Impedance.Unit.Ohm)=ImpedanceField("",unit,false,null)
    }
}

data class CurrentField(
    val input: String,
    val unit: Current.Unit,
    val isValid:Boolean,
    val error: IText?,
    val isVisible:Boolean=true

){
    val value get()= input be unit

    val notValid=!isValid

    fun validate(value: String,unit: Current.Unit,validator:CurrentValidator={s,_->Validator.validate.positiveNumber(s,"")}):CurrentField{
        val validationResult= validator(value,unit)
        return copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
    }
    companion object{
        fun empty(unit:Current.Unit=Current.Unit.A)=CurrentField("",unit,false,null)
    }
}


data class CoincidenceFactorField(val label: String,val input: String,val isValid:Boolean,val error: IText?,val isVisible:Boolean=true){

    val value get()= CoincidenceFactor(input.toDouble())
    val notValid=!isValid

    fun validate(value: String,validator:(String)->IText?={Validator.validate.inRange(it,.1,1.0,"")}):CoincidenceFactorField{
        val validationResult= validator(value)
        return copy(input=value,isValid = validationResult==null,error = validationResult)
    }

    companion object{
        fun empty(label: String= "Coincidence factor")=CoincidenceFactorField(label,"",false,null)
        fun valid(label: String= "Coincidence factor",value: CoincidenceFactor)=CoincidenceFactorField(label,value.value.format(),true,null)
    }
}

data class VoltageField(val input: String,
                        val unit: Voltage.Unit,
                        val isValid:Boolean,
                        val error: IText?,
                        val isVisible:Boolean=true){

    val notValid=!isValid
    val value get()= input be unit

    fun validate(value: String,unit: Voltage.Unit,validator:(String,Voltage.Unit)->IText?={s,_->Validator.validate.positiveNumber(s,"")}):VoltageField{
        val validationResult= validator(value,unit)
        return copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
    }

    companion object{
        fun empty(unit:Voltage.Unit=Voltage.Unit.V)=VoltageField("",unit,false,null)
        fun valid(value:Voltage)=VoltageField(value.value.format(),value.unit,true,null)
    }
}

data class WorkingVoltageField(val label: String="Voltage",
                               val input: String,
                               val system: PowerSystem,
                               val isValid:Boolean,
                               val error: IText?,
                               val isVisible:Boolean=true){

    val notValid=!isValid
    val voltage:WorkingVoltage get(){
        val v=input be Voltage.Unit.V
        return WorkingVoltage(v,system)
    }

    companion object{
        fun empty(label: String="Voltage",value: String="",system: PowerSystem=PowerSystem.ThreePhase)=WorkingVoltageField(label,value,system ,false,null)
        fun validField(label: String="Voltage",value: String="",system: PowerSystem=PowerSystem.ThreePhase)=WorkingVoltageField(label,value,system ,true,null)
        fun validField(label: String="Voltage",value: Voltage,system: PowerSystem=PowerSystem.ThreePhase)=WorkingVoltageField(label,value.value.format(),system ,true,null)
    }
}

data class FrequencyField(val input: String,
                        val second: Frequency.Unit,
                        val isValid:Boolean,
                        val error: IText?,
                        val isVisible:Boolean=true){

    val notValid=!isValid
    val value get()= input be second

    companion object{
        fun empty(unit:Frequency.Unit=Frequency.Unit.Hz)=FrequencyField("",unit,false,null)
        fun valid(value:Frequency)=FrequencyField(value.value.toString(),value.unit,true,null)
    }
}

data class CosPhiField(val label: String, val input: String, val isValid:Boolean, val error: IText?, val isVisible:Boolean=true){

    val value get()=CosPhi(input.toDouble())
    val notValid=!isValid
    fun validate(value: String,validator:(String)->IText?={Validator.validate.inRange(it,.1,1.0,"")}):CosPhiField{
        val validationResult= validator(value)
        return copy(input=value,isValid = validationResult==null,error = validationResult)
    }
    companion object{
        fun empty(label: String= CosPhi)=CosPhiField(label,"",false,null)
        fun valid(label: String= CosPhi,value: CosPhi)=CosPhiField(label,value.value.format(),true,null)
    }
}

data class SpeedField(val label: String,val input: String,val isValid:Boolean,val error: IText?,val isVisible:Boolean=true){

    val value get()=Speed(input.toDouble())
    val notValid=!isValid

    fun validate(value: String,validator:(String)->IText?={s->Validator.validate.positiveNumber(s,"")}):SpeedField{
        val validationResult= validator(value)
        return copy(input=value,isValid = validationResult==null,error = validationResult)
    }
    companion object{
        fun empty(label: String)=SpeedField(label,"",false,null)
        fun valid(label: String,value:String)=SpeedField(label,value,true,null)
        fun valid(label: String,value:Speed)=SpeedField(label,value.value.format(),true,null)
    }
}


data class SlipFactorField(val input: String,val isValid:Boolean,val error: IText?,val isVisible:Boolean=true){

    val value get()=SlipFactor(input.toDouble())
    val notValid=!isValid
    fun validate(value: String,validator:(String)->IText?={Validator.validate.inRange(it,1.0,100.0,"")}):SlipFactorField{
        val validationResult= validator(value)
        return copy(input=value,isValid = validationResult==null,error = validationResult)
    }
    companion object{
        fun empty()=SlipFactorField("",false,null)
        fun valid(value:String)=SlipFactorField(value,true,null)
    }
}

data class BooleanField(val label: String,
                        val value:Boolean=false,
                        val isValid:Boolean=true,
                        val error: IText?=null,
                        val isVisible:Boolean=true,
                        val trueText:String ="Yes",
                        val falseText:String="No"){

    val notValid=!isValid

}

data class EfficiencyField(val input: String, val isValid:Boolean, val error: IText?, val isVisible:Boolean=true){

    val efficiency get()=Efficiency(input.toDouble())
    val notValid=!isValid

    fun validate(value: String,validator:(String)->IText?={s->Validator.validate.positiveNumber(s,"")}):EfficiencyField{
        val validationResult= validator(value)
        return copy(input=value,isValid = validationResult==null,error = validationResult)
    }

    companion object{
        fun empty()=EfficiencyField("",false,null)
        fun valid(value: Efficiency)=EfficiencyField(value.value.format(),true,null)
    }
}

data class SpeedFactorField(val input: String,val isValid:Boolean,val error: IText?,val isVisible:Boolean=true){

    val value get()=SlipFactor(input.toDouble())
    val notValid=!isValid
    companion object{
        fun empty()=SpeedFactorField("",false,null)
    }
}



data class LengthField(
    val value: String,
    val second: Length.Unit,
    val isValid:Boolean,
    val error: IText?,
    val isVisible:Boolean=true

){
    val notValid=!isValid
    companion object{
        fun empty(unit:Length.Unit=Length.Unit.M)=LengthField("",unit,false,null)
    }
}

data class StringField(val value: String,
                       val second: String,
                       val isValid:Boolean,
                       val error: IText?,
                       val isVisible:Boolean=true){
    val notValid=!isValid
 companion object{
     fun empty(suffix:String="")=StringField("",suffix,false,null)
     fun valid(suffix:String="",value: String)=StringField(value,suffix,true,null)
 }
}









