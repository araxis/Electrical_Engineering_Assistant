package com.vm.eea.ui.calculators

sealed class FormState<T> {

    data class Calculating<T>(val value: String):FormState<T>()
    data class Ready<T> (val value:T):FormState<T>()
    data class Failed<T>(val error: String):FormState<T>()
}