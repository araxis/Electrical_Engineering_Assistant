package com.vm.eea.ui.project.updateProjectMaxvoltDrop

import com.vm.eea.application.project.RelationType
import com.vm.eea.utilities.IText

data class UiState(val pageTitle:String, val value:String, val error:IText?, val canExecute:Boolean) {
    companion object{
        fun init(relationType: RelationType):UiState{
            val pageTitle=when(relationType){
                RelationType.PanelToPanel -> "Panel To Panel Max Volt drop"
                RelationType.PanelToMotor -> "Panel To Motor Max Volt drop"
            }

            return UiState(pageTitle,"",null,false)
        }
    }
}