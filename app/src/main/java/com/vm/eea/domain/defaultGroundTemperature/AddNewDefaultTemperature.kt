package com.vm.eea.domain.defaultGroundTemperature

import com.vm.eea.domain.Environment
import com.vm.eea.domain.UnitOfTemperature
import com.vm.eea.utilities.be

class AddNewDefaultTemperature(private val repository: IDefaultTemperatureRepository) {

   suspend  operator fun invoke(value:Number,unit:UnitOfTemperature,environment: Environment){
        val temp= value be unit
       val item= DefaultTemperature(temp,true,environment,0)
       repository.insert(item)
    }

}