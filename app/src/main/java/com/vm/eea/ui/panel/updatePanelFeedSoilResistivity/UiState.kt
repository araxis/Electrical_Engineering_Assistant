package com.vm.eea.ui.panel.updatePanelFeedSoilResistivity

import com.vm.eea.application.SelectableItem
import com.vm.eea.application.ThermalResistivity

data class UiState(val items:List<SelectableItem<ThermalResistivity>> = emptyList())