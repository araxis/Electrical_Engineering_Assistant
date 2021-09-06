package com.vm.eea.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vm.eea.data.model.DefaultWireSizeEntity
import com.vm.eea.domain.UnitOfWireSize
import kotlinx.coroutines.flow.Flow

@Dao
interface IDefaultWireSizeDao {
    @Query("Select * from default_wires")
    fun getFlow(): Flow<List<DefaultWireSizeEntity>>

    @Query("Select * from default_wires where unit= :sizeType")
    fun getFlow(sizeType:UnitOfWireSize): Flow<List<DefaultWireSizeEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM default_wires WHERE value= :value and unit= :unit LIMIT 1);")
    suspend fun isExist(value:Double,unit: UnitOfWireSize):Boolean

    @Insert
    suspend fun insert(item:DefaultWireSizeEntity)

}