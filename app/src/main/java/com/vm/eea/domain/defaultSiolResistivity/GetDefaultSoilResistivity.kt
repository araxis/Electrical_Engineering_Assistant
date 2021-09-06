package com.vm.eea.domain.defaultSiolResistivity

class GetDefaultSoilResistivity(private val repository: IDefaultSoilResistivityRepository) {

     operator fun invoke()=repository.getValuesFlow()
}