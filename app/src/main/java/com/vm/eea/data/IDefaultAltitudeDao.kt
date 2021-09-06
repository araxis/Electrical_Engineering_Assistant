package com.vm.eea.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vm.eea.data.model.DefaultAltitudeEntity
import com.vm.eea.domain.UnitOfLength
import kotlinx.coroutines.flow.Flow

@Dao
interface IDefaultAltitudeDao {
    @Query("Select * from default_altitudes")
    fun getDefaultsFlow(): Flow<List<DefaultAltitudeEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM default_altitudes WHERE value= :value and unit= :unit LIMIT 1);")
    suspend fun isExist(value:Double,unit: UnitOfLength):Boolean


    @Insert
    suspend fun insert(item:DefaultAltitudeEntity)

}