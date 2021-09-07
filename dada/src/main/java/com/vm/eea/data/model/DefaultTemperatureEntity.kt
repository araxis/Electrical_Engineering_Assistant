package com.vm.eea.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vm.eea.domain.Environment
import com.vm.eea.domain.Temperature

@Entity(tableName = "default_temperatures")
data class DefaultTemperatureEntity(
    @Embedded
    val value: Temperature,
    val environment: Environment,
    val isCustom: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)
