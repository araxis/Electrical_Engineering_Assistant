package com.vm.eea.data.repositories


import com.vm.eea.data.AppDatabase
import com.vm.eea.domain.appDefaults.IAppDefaultsRepository
import com.vm.eea.domain.defaultAltitude.AppDefaults

class AppDefaultsRepository(private val db: AppDatabase): IAppDefaultsRepository {

    override suspend fun getDefaults(): AppDefaults {
        return db.defaultsDao().getProjectDefaults().toDomainModel()
    }
}