package com.vm.eea.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wire_size_map_table")
data class WireSizeMapEntity(val mm2: Double, val awg:Double, val kcmil:Double,
                       @PrimaryKey(autoGenerate = true)val id:Long=0)
