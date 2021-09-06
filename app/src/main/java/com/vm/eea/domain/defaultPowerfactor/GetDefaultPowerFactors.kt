package com.vm.eea.domain.defaultPowerfactor

import com.vm.eea.domain.PowerSystem

class GetDefaultPowerFactors(private val defaultPowerfactorRepository: IDefaultPowerfactorRepository) {

    operator fun invoke()=defaultPowerfactorRepository.get()
    operator fun invoke(system: PowerSystem) =defaultPowerfactorRepository.get(system )
}