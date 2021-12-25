package com.vm.eea.ui.home

import com.vm.eea.ui.MenuItem

data class UiState(val calculators:List<MenuItem> = emptyList(),
              val projects:List<MenuItem> = emptyList())