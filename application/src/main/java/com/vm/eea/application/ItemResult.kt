package com.vm.eea.application

sealed class ItemResult<T> {
    data class Found<T>(val value:T):ItemResult<T>()
    class NotFound<T>:ItemResult<T>()
}
