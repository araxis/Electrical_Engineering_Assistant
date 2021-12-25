package com.vm.eea.ui.calculators.fullPanelCalculator

sealed class Effect{
    class ShowMotorMenu():Effect()
    class HideMenu:Effect()
    class Share:Effect()
}