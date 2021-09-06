package com.vm.eea.domain.defaultGroundTemperature

import com.vm.eea.domain.Environment

class GetDefaultTemperatures(private val repository: IDefaultTemperatureRepository) {

     operator fun invoke(environment: Environment)=repository.getValuesFlow(environment)
}