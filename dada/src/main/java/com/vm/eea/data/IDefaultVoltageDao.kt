package com.vm.eea.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vm.eea.data.model.DefaultVoltageEntity
import com.vm.eea.domain.PowerSystem
import kotlinx.coroutines.flow.Flow

@Dao
interface IDefaultVoltageDao {
    @Query("Select * from default_voltages")
    fun getDefaultsFlow(): Flow<List<DefaultVoltageEntity>>

    @Query("Select * from default_voltages where system = :system")
    fun getDefaultsFlow(system: PowerSystem): Flow<List<DefaultVoltageEntity>>

    @Insert
    suspend fun insert(item:DefaultVoltageEntity)

}