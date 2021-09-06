package com.vm.eea.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.Voltage

@Entity(tableName = "default_voltages")
data class DefaultVoltageEntity(@Embedded
                                val voltage: Voltage,
                                val system:PowerSystem,
                                val isCustom:Boolean,
                                @PrimaryKey(autoGenerate = true) val id:Long=0)
