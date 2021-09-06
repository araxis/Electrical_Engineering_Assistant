package com.vm.eea.domain.appDefaults

interface IAppDefaultsRepository {

    suspend fun getDefaults(): AppDefaults

}