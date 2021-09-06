package com.vm.eea.ui.panel.addPanle

import com.vm.eea.domain.panel.Panel
import com.vm.eea.utilities.IText

data class UiState(val code:String,
                   val description:String,
                   val length: String,
                   val feeder:Panel?,
                   val feeders:List<Panel>,
                   val codeError:IText?,
                   val lengthError:IText?,
                   val canSubmit:Boolean
                   ){
    companion object{
        fun init() = UiState("","", "10",null, emptyList(),
            null,null,false)
    }
}

