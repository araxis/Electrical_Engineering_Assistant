package com.vm.eea.ui.motor.addMotor

import com.vm.eea.application.*
import com.vm.eea.application.panel.SimplePanel
import com.vm.eea.ui.LengthField
import com.vm.eea.ui.PowerField
import com.vm.eea.ui.StringField

import com.vm.eea.utilities.toSelectableList


data class UiState(
    val code: StringField = StringField.empty(),
    val power: PowerField = PowerField.empty(),
    val powerfactor: CosPhi =CosPhi(.9),
    val efficiency: Efficiency =Efficiency(90),
    val feedLength: LengthField = LengthField.empty(Length.Unit.M),
    val feeder: SimplePanel?=null,
    val system: PowerSystem = PowerSystem.ThreePhase,
    val startMode: StartMode=StartMode.Dol,
    val selector: Selector = Selector.None,
    val feeders: List<SelectableItem<SimplePanel>> = emptyList(),
    val systems:List<SelectableItem<PowerSystem>> = toSelectableList(PowerSystem.ThreePhase),
    val startModes:List<SelectableItem<StartMode>> = toSelectableList(StartMode.Dol),
    val canSubmit: Boolean = false,
){
    enum class Selector{
        Feeder,System,StartMode,None
    }
}