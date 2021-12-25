package com.vm.eea.ui.motor.motorResult

sealed class Effect{
    class Share: Effect()
    class None: Effect()
}
