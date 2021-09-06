package com.vm.eea.domain.defaultAltitude

import com.vm.eea.domain.UnitOfLength
import com.vm.eea.utilities.be

class AddNewDefaultAltitude(private val repository: IDefaultAltitudeRepository) {

   suspend  operator fun invoke(value:Number,unit:UnitOfLength){
        val length= value be unit
       val item=DefaultAltitude(length,true,0)
       repository.insert(item)
    }

}