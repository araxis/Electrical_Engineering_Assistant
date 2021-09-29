package com.vm.eea.ui.panel.addPanle

sealed class Effect {
     object None:Effect()
     class ShowModal : Effect()
}