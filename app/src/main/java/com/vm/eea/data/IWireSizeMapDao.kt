package com.vm.eea.data

import androidx.room.Dao
import androidx.room.Query
import com.vm.eea.data.model.WireSizeMapEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IWireSizeMapDao {
    @Query("Select * from wire_size_map_table")
    fun getFlow(): Flow<List<WireSizeMapEntity>>

}