package com.vm.eea.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vm.eea.data.model.DefaultTemperatureEntity
import com.vm.eea.domain.Environment
import com.vm.eea.domain.UnitOfTemperature
import kotlinx.coroutines.flow.Flow

@Dao
interface IDefaultTemperatureDao {
    @Query("Select * from default_temperatures")
    fun getDefaultsFlow(): Flow<List<DefaultTemperatureEntity>>

    @Query("Select * from default_temperatures where environment= :environment")
    fun getDefaultsFlow(environment: Environment): Flow<List<DefaultTemperatureEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM default_temperatures WHERE value= :value and unit= :unit and environment= :environment LIMIT 1);")
    suspend fun isExist(value:Double,unit: UnitOfTemperature,environment: Environment):Boolean


    @Insert
    suspend fun insert(item:DefaultTemperatureEntity)

}