package com.vm.eea.data.repositories

import com.vm.eea.domain.appDefaults.AppDefaults
import com.vm.eea.domain.appDefaults.IAppDefaultsRepository
import com.vm.eea.data.AppDatabase

class AppDefaultsRepository(private val db:AppDatabase): IAppDefaultsRepository {

    override suspend fun getDefaults(): AppDefaults {
        return db.defaultsDao().getProjectDefaults().toDomainModel()
    }
}