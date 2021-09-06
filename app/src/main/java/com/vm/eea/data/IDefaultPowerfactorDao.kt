package com.vm.eea.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vm.eea.data.model.DefaultPowerFactorEntity
import com.vm.eea.domain.PowerSystem
import kotlinx.coroutines.flow.Flow

@Dao
interface IDefaultPowerfactorDao {
    @Query("Select * from default_power_factors")
    fun getFlow(): Flow<List<DefaultPowerFactorEntity>>

    @Query("Select * from default_power_factors where system = :system")
    fun getFlow(system:PowerSystem): Flow<List<DefaultPowerFactorEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM default_power_factors WHERE value= :value and system= :system LIMIT 1);")
    suspend fun isExist(value:Double,system: PowerSystem):Boolean

    @Insert
    suspend fun insert(item:DefaultPowerFactorEntity)

}