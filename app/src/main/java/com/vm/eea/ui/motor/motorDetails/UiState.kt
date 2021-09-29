package com.vm.eea.ui.motor.motorDetails

import com.vm.eea.ui.PropertyItem

data class UiState(val items:List<PropertyItem>,val calculatedInfo:List<Pair<String,String>> = emptyList())