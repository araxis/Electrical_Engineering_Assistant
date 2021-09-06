package com.vm.eea.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vm.eea.domain.PowerFactor
import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.defaultPowerfactor.DefaultPowerfactor

@Entity(tableName = "default_power_factors")
data class DefaultPowerFactorEntity(
    val value: Double,
    val system: PowerSystem,
    val isCustom:Boolean,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
){
    fun toDomain()=DefaultPowerfactor(PowerFactor(value),system,isCustom ,id)
}
