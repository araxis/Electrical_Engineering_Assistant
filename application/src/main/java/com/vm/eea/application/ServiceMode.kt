package com.vm.eea.application

enum class ServiceMode {
    Service,Standby,Spare ;

    operator fun invoke()=name
}