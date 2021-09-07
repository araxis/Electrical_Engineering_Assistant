package com.vm.eea.domain.appDefaults

import com.vm.eea.domain.defaultAltitude.AppDefaults

interface IAppDefaultsRepository {

    suspend fun getDefaults(): AppDefaults

}