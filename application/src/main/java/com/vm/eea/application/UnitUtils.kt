package com.vm.eea.application


import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.project.ProjectId
import java.text.DecimalFormat


fun Any?.isNotNull(action:()->Unit){
    if(this!=null )action()
}

fun String.toDoubleOr(value:Double=0.0) :Double{
    if(isNotBlank()) return value
    return toDouble()
}

    infix fun Number.be(unitOfTemperature: UnitOfTemperature) = Temperature(toDouble(),unitOfTemperature)
    infix fun String.be(unitOfTemperature: UnitOfTemperature) = toDouble() be unitOfTemperature

    infix fun Number.be(unit: Power.Unit) = Power(toDouble(),unit)
    infix fun String.be(unit: Power.Unit) = (toDoubleOrNull()?:0) be unit

    infix fun Number.be(unit: ApparentPower.Unit) = ApparentPower(toDouble(),unit)
    infix fun String.be(unit: ApparentPower.Unit) = (toDoubleOrNull()?:0) be unit

    infix fun Number.be(unit: ReactivePower.Unit) = ReactivePower(toDouble(),unit)
    infix fun String.be(unit: ReactivePower.Unit) = (toDoubleOrNull()?:0) be unit

    infix fun Number.be(unitOfLength: Length.Unit) = Length(toDouble(),unitOfLength)
    infix fun String.be(unitOfLength: Length.Unit) = (toDoubleOrNull()?:0) be unitOfLength

    infix fun Number.be(unitOfVoltage: Voltage.Unit) = Voltage(toDouble(),unitOfVoltage)
    infix fun String.be(unitOfVoltage: Voltage.Unit) = (toDoubleOrNull()?:0) be unitOfVoltage

    infix fun Number.be(unitOfThermalResistivity: UnitOfThermalResistivity) = ThermalResistivity(toDouble(),unitOfThermalResistivity)
    infix fun String.be(unitOfThermalResistivity: UnitOfThermalResistivity) = (toDoubleOrNull()?:0) be unitOfThermalResistivity

    infix fun Number.be(unit: Frequency.Unit) = Frequency(toDouble(),unit)
    infix fun String.be(unit: Frequency.Unit) = (toDoubleOrNull()?:0) be unit

    infix fun Number.be(unit: UnitOfWireSize) = WireSize(toDouble(),unit)
    infix fun String.be(unit: UnitOfWireSize) = (toDoubleOrNull()?:0) be unit

    infix fun Number.be(unit: Current.Unit) = Current(this,unit)
    infix fun String.be(unit: Current.Unit) = (toDoubleOrNull()?:0) be unit

    infix fun Number.be(unit: Resistance.Unit) = Resistance(this,unit)
    infix fun String.be(unit: Resistance.Unit) = (toDoubleOrNull()?:0) be unit

    infix fun Number.be(unit: Impedance.Unit) = Impedance(this,unit)
    infix fun String.be(unit: Impedance.Unit) = (toDoubleOrNull()?:0) be unit

val Number.toPowerfactor get()= CosPhi(this)
val Number.toEfficiency get()= Efficiency(this)
fun Long?.toMotorId()= MotorId(this?:-1)
fun Long?.toPanelId()= PanelId(this?:-1)
fun Long?.toProjectId()= ProjectId(this?:-1)
fun Long?.toRelationId()= RelationId(this?:-1)

    val Number.voltDrop get()= VoltDrop(this)
    val Number.VA get()= ApparentPower(this, ApparentPower.Unit.VA)
    val Number.KVA get()= ApparentPower(this, ApparentPower.Unit.KVA)
    val Number.mm2 get()= WireSize(this, UnitOfWireSize.MM2)
    val Number.v: Voltage
    get() = Voltage(this.toDouble(), unitOfVoltage = Voltage.Unit.V)

     val Number.A get()= Current(this,Current.Unit.A)
      val Number.KA get()= Current(this,Current.Unit.KA)
      val Number.VAr get()= ReactivePower(this,ReactivePower.Unit.VAr)
      val Number.KVAr get()= ReactivePower(this,ReactivePower.Unit.KVAr)
    val Number.c: Temperature
    get() = Temperature(this.toDouble(), unit = UnitOfTemperature.C)

    val Number.m: Length
    get() = Length(this.toDouble(), unit = Length.Unit.M)

    val Number.KW: Power
    get() = Power(this.toDouble(), unit = Power.Unit.KW)

val Number.HZ: Frequency
    get() = Frequency(this.toDouble(), unit = Frequency.Unit.Hz)

    val Number.W: Power
    get() = Power(this.toDouble(), unit = Power.Unit.W)



    fun Double.format(pattern:String="###.##",empty:String=""):String{
        if(this == 0.0) return empty
        val formatter = DecimalFormat(pattern)
        return formatter.format(this)
    }
