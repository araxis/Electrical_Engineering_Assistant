package com.vm.eea.application.panel

@JvmInline
value class PanelCode(val value:String) {
    init {
        require(value.isNotBlank()){"value must not be empty"}
    }
}