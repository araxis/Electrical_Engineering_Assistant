package com.vm.eea.ui.project.updateProjectMaxvoltDrop

import com.vm.eea.domain.RelationType
import com.vm.eea.utilities.IText

data class UpdateProjectMaxVoltDropState(val pageTitle:String,val value:String,val error:IText?,val canExecute:Boolean) {
    companion object{
        fun init(relationType: RelationType):UpdateProjectMaxVoltDropState{
            val pageTitle=when(relationType){
                RelationType.PanelToPanel -> "Panel To Panel Max Volt drop"
                RelationType.PanelToMotor -> "Panel To Motor Max Volt drop"
            }

            return UpdateProjectMaxVoltDropState(pageTitle,"",null,false)
        }
    }
}