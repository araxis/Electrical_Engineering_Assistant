package com.vm.eea.ui.project.updateProjectTemperature

import com.vm.eea.domain.Environment
import com.vm.eea.domain.Temperature
import com.vm.eea.domain.UnitOfTemperature
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.IText

data class UpdateProjectTemperatureState(val pageTitle:String, val value:String,
                                         val unit: UnitOfTemperature,
                                         val error: IText?,
                                         val canExecute:Boolean,
                                         val addToDefaults:Boolean,
                                         val defaults:List<SelectableItem<Temperature>>){
    companion object{
        fun init(environment: Environment):UpdateProjectTemperatureState {
            val title=when(environment){
                Environment.Ambient -> "Ambient Temperature"
                Environment.Ground -> "Ground Temperature"
            }
        return UpdateProjectTemperatureState(title,"",
            UnitOfTemperature.C,
            null,
            canExecute = false,
            addToDefaults = true,
            defaults = emptyList())
    }
}
}

data class DefaultGroundTemperatureItem(val value:Temperature, val isCustom:Boolean, val isSelected:Boolean)
