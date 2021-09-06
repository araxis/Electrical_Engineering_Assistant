package com.vm.eea.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vm.eea.domain.Length

@Entity(tableName = "default_altitudes")
data class DefaultAltitudeEntity(
    @Embedded
    val value: Length,
    val isCustom: Boolean, @PrimaryKey(autoGenerate = true) val id: Long = 0,
)
