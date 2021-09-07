package com.vm.eea.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vm.eea.domain.defaultWireSize.DefaultWireSize
import com.vm.eea.domain.WireSize

@Entity(tableName = "default_wires")
data class DefaultWireSizeEntity(@Embedded val wireSize: WireSize, val isCustom:Boolean, @PrimaryKey(autoGenerate = true) val id:Long=0){

    fun toDomain()= DefaultWireSize(wireSize,isCustom,id)
}
