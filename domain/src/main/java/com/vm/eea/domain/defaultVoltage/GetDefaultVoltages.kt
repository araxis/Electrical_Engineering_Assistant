package com.vm.eea.domain.defaultVoltage

import com.vm.eea.domain.PowerSystem
import kotlinx.coroutines.flow.Flow

class GetDefaultVoltages(private val defaultVoltageRepository: IDefaultVoltageRepository) {
     operator fun invoke(system: PowerSystem): Flow<List<DefaultVoltage>> =
        defaultVoltageRepository.getValuesFlow(system)
}