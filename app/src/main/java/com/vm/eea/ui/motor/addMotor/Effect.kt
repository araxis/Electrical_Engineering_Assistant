package com.vm.eea.ui.motor.addMotor

sealed class Effect {
    data class Toast(val text: String) : Effect()
}