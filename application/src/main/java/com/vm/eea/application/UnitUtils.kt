package com.vm.eea.application


import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.project.ProjectId
import java.text.DecimalFormat


fun Any?.isNotNull(action:()->Unit){
    if(this!=null )action()
}


        infix fun Number.be(unitOfTemperature: UnitOfTemperature) = Temperature(toDouble(),unitOfTemperature)
    infix fun String.be(unitOfTemperature: UnitOfTemperature) = toDouble() be unitOfTemperature

    infix fun Number.be(unit: Power.Unit) = Power(toDouble(),unit)
    infix fun String.be(unit: Power.Unit) = toDouble() be unit

    infix fun Number.be(unitOfLength: Length.Unit) = Length(toDouble(),unitOfLength)
    infix fun String.be(unitOfLength: Length.Unit) = toDouble() be unitOfLength

    infix fun Number.be(unitOfVoltage: Voltage.Unit) = Voltage(toDouble(),unitOfVoltage)
    infix fun String.be(unitOfVoltage: Voltage.Unit) = toDouble() be unitOfVoltage

    infix fun Number.be(unitOfThermalResistivity: UnitOfThermalResistivity) = ThermalResistivity(toDouble(),unitOfThermalResistivity)
    infix fun String.be(unitOfThermalResistivity: UnitOfThermalResistivity) = toDouble() be unitOfThermalResistivity

    infix fun Number.be(unit: UnitOfWireSize) = WireSize(toDouble(),unit)
    infix fun String.be(unit: UnitOfWireSize) = toDouble() be unit

    infix fun Number.be(unit: Current.Unit) = Current(this,unit)
    infix fun String.be(unit: Current.Unit) = toDouble() be unit

val Number.toPowerfactor get()= CosPhi(this)
val Number.toEfficiency get()= Efficiency(this)
fun Long.toMotorId()= MotorId(this)
fun Long.toPanelId()= PanelId(this)
fun Long.toProjectId()= ProjectId(this)
fun Long.toRelationId()= RelationId(this)
//infix fun Number.be(unit:CurrentType) = Current(toDouble(),unit)
//infix fun String.be(unit:CurrentType) = toDouble() be unit
//
//infix fun Number.be(system: PowerSystem) = WorkingVoltage(this.v,system)
//infix fun String.be(system: PowerSystem) = toDouble() be system
//
//infix fun Voltage.be(system: PowerSystem) = WorkingVoltage(this,system)
//
//
//
//val Number.threePhase: WorkingVoltage
//    getPanels() = WorkingVoltage(this.v,system = PowerSystem.ThreePhase)
//
//val Voltage.threePhase:WorkingVoltage
//        getPanels() = WorkingVoltage(this,PowerSystem.ThreePhase)
    val Number.voltDrop get()= VoltDrop(this)
    val Number.VA get()= ApparentPower(this, ApparentPower.Unit.VA)
    val Number.mm2 get()= WireSize(this, UnitOfWireSize.MM2)
    val Number.v: Voltage
    get() = Voltage(this.toDouble(), unitOfVoltage = Voltage.Unit.V)

    val Number.c: Temperature
    get() = Temperature(this.toDouble(), unit = UnitOfTemperature.C)

    val Number.m: Length
    get() = Length(this.toDouble(), unit = Length.Unit.M)

    val Number.KW: Power
    get() = Power(this.toDouble(), unit = Power.Unit.KW)

    val Number.W: Power
    get() = Power(this.toDouble(), unit = Power.Unit.W)

    val String.ifZeroEmpty:String
    get() {
        if(isBlank()) return ""
        if(this.toDouble()==0.0) return ""
        return this
    }

    fun Double.format(pattern:String="###.###",empty:String=""):String{
        if(this == 0.0) return empty
        val formatter = DecimalFormat(pattern)
        return formatter.format(this)
    }
