package com.vm.eea.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vm.eea.domain.ThermalResistivity

@Entity(tableName = "default_soil_resistivities")
data class DefaultSoilResistivityEntity(
    @Embedded
    val value: ThermalResistivity,
    val isCustom: Boolean, @PrimaryKey(autoGenerate = true) val id: Long = 0,
)
