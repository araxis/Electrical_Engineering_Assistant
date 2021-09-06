package com.vm.eea.ui.motor.addMotor

import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.UnitOfLength
import com.vm.eea.domain.UnitOfPower
import com.vm.eea.ui.*
import com.vm.eea.ui.models.SimplePanel
import com.vm.eea.utilities.toSelectableList



data class UiState(
    val code: StringField = Field.empty(),
    val power: PowerField = Field.empty(UnitOfPower.W),
    val powerfactor: PowerfactorField = Field.empty(""),
    val efficiency: EfficiencyField = Field.empty("%"),
    val demandFactor: PowerfactorField = Field.empty(""),
    val feedLength: LengthField = Field.empty(UnitOfLength.M),
    val feeder: SimplePanel?=null,
    val system: PowerSystem = PowerSystem.ThreePhase,
    val selector: Selector = Selector.None,
    val feeders: List<SelectableItem<SimplePanel>> = emptyList(),
    val systems:List<SelectableItem<PowerSystem>> = toSelectableList(PowerSystem.ThreePhase),
    val canSubmit: Boolean = false,
){
    enum class Selector{
        Feeder,System,None
    }
}