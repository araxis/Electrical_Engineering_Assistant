package com.vm.eea.domain

enum class ServiceMode {
    Service,Standby,Spare ;

    operator fun invoke()=name
}