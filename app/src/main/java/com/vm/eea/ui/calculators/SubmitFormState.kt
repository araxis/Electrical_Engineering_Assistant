package com.vm.eea.ui.calculators

sealed class SubmitFormState<T>  {
    data class Filling<T>(val value: String):SubmitFormState<T>()
    data class ResultReady<T> (val value:T):SubmitFormState<T>()
    class ReadyToCalculate<T> :SubmitFormState<T>()

}