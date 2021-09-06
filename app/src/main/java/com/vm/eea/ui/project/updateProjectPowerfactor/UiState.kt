package com.vm.eea.ui.project.updateProjectPowerfactor

import com.vm.eea.domain.PowerFactor
import com.vm.eea.domain.PowerSystem
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.IText

data class UiState(
    val pageTitle:String,
    val value: String,
    val error: IText?,
    val defaults: List<SelectableItem<PowerFactor>>,
    val canSubmit: Boolean,
) {
    companion object{
        fun init(system: PowerSystem):UiState{
            val pageTitle=when(system){
                PowerSystem.SinglePhase -> "single-phase Powerfactor"
                PowerSystem.ThreePhase -> "three-phase Powerfactor"
                PowerSystem.TwoPhase -> TODO()
            }
           return UiState(pageTitle,"",null, emptyList(),false)
        }
    }
}
