package com.vm.eea.ui.panel.addPanle


import com.vm.eea.application.Length
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panel.SimplePanel
import com.vm.eea.ui.StringField
import com.vm.eea.utilities.IText

data class UiState(val code:String,
                   val description:String,
                   val length: String,
                   val lengthUnit: Length.Unit,
                   val factorCorrection:Boolean,
                   val demandFactor:StringField,
                   val feeder: SimplePanel?,
                   val feeders:List<SelectableItem<SimplePanel>>,
                   val codeError:IText?,
                   val lengthError:IText?,
                   val canSubmit:Boolean
                   ){
    companion object{
        fun init() = UiState("","", "10",Length.Unit.M,false, StringField.empty(),
            feeder = null,
            feeders = emptyList(),
            codeError = null,
            lengthError = null,
            canSubmit = false
        )
    }
}

