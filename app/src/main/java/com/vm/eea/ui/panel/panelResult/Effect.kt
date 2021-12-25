package com.vm.eea.ui.panel.panelResult

sealed class Effect{
    class ShowMotorMenu():Effect()
    class HideMenu:Effect()
    class Share:Effect()
}