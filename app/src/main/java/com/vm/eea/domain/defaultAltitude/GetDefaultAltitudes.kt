package com.vm.eea.domain.defaultAltitude

class GetDefaultAltitudes(private val repository: IDefaultAltitudeRepository) {

     operator fun invoke()=repository.getValuesFlow()
}