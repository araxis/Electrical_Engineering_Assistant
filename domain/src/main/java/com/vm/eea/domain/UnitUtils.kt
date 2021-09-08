package com.vm.eea.domain

import com.vm.eea.domain.project.ProjectId
import java.text.DecimalFormat


fun Any?.isNotNull(action:()->Unit){
    if(this!=null )action()
}


        infix fun Number.be(unitOfTemperature: UnitOfTemperature) = Temperature(toDouble(),unitOfTemperature)
    infix fun String.be(unitOfTemperature: UnitOfTemperature) = toDouble() be unitOfTemperature

    infix fun Number.be(unitOfPower: UnitOfPower) = Power(toDouble(),unitOfPower)
    infix fun String.be(unitOfPower: UnitOfPower) = toDouble() be unitOfPower

    infix fun Number.be(unitOfLength: UnitOfLength) = Length(toDouble(),unitOfLength)
    infix fun String.be(unitOfLength: UnitOfLength) = toDouble() be unitOfLength

    infix fun Number.be(unitOfVoltage: UnitOfVoltage) = Voltage(toDouble(),unitOfVoltage)
    infix fun String.be(unitOfVoltage: UnitOfVoltage) = toDouble() be unitOfVoltage

    infix fun Number.be(unitOfThermalResistivity: UnitOfThermalResistivity) = ThermalResistivity(toDouble(),unitOfThermalResistivity)
    infix fun String.be(unitOfThermalResistivity: UnitOfThermalResistivity) = toDouble() be unitOfThermalResistivity

    infix fun Number.be(unit: UnitOfWireSize) = WireSize(toDouble(),unit)
    infix fun String.be(unit: UnitOfWireSize) = toDouble() be unit


val Number.toPowerfactor get()= PowerFactor(this)
val Number.toEfficiency get()= Efficiency(this)
fun Long.toMotorId()=LoadId(this)
fun Long.toPanelId()=PanelId(this)
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
    val Number.mm2 get()= WireSize(this, UnitOfWireSize.MM2)
    val Number.v: Voltage
    get() = Voltage(this.toDouble(), unitOfVoltage = UnitOfVoltage.V)

    val Number.c: Temperature
    get() = Temperature(this.toDouble(), unit = UnitOfTemperature.C)

    val Number.m: Length
    get() = Length(this.toDouble(), unit = UnitOfLength.M)

    val Number.KW: Power
    get() = Power(this.toDouble(), unit = UnitOfPower.KW)

    val Number.W: Power
    get() = Power(this.toDouble(), unit = UnitOfPower.W)

    val String.ifZeroEmpty:String
    get() {
        if(isBlank()) return ""
        if(this.toDouble()==0.0) return ""
        return this
    }

    fun Double.format(pattern:String="###.###"):String{
        if(this == 0.0) return ""
        val formatter = DecimalFormat(pattern)
        return formatter.format(this)
    }
