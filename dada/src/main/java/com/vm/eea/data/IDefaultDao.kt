package com.vm.eea.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vm.eea.data.model.DefaultsEntity

@Dao
interface IDefaultDao {

    @Query("SELECT * FROM defaults ORDER BY ROWID ASC LIMIT 1")
    fun getProjectDefaults(): DefaultsEntity

    @Insert
    fun insert(item:DefaultsEntity)
}