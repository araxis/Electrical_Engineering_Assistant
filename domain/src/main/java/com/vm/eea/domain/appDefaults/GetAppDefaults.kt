package com.vm.eea.domain.appDefaults

import com.vm.eea.domain.defaultAltitude.AppDefaults

class GetAppDefaults(private val appDefaultsRepository: IAppDefaultsRepository) {

    suspend operator fun invoke(): AppDefaults {
        return appDefaultsRepository.getDefaults()
    }

}
