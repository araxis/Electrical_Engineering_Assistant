package com.vm.eea.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vm.eea.data.model.DefaultSoilResistivityEntity
import com.vm.eea.domain.UnitOfThermalResistivity
import kotlinx.coroutines.flow.Flow

@Dao
interface IDefaultSoilResistivityDao {
    @Query("Select * from default_soil_resistivities")
    fun getDefaultsFlow(): Flow<List<DefaultSoilResistivityEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM default_soil_resistivities WHERE value= :value and unit= :unit LIMIT 1);")
    suspend fun isExist(value:Double, unit: UnitOfThermalResistivity):Boolean


    @Insert
    suspend fun insert(item:DefaultSoilResistivityEntity)

}