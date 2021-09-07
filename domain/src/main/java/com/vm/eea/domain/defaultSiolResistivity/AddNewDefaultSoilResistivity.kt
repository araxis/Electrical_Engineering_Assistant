package com.vm.eea.domain.defaultSiolResistivity

import com.vm.eea.domain.UnitOfThermalResistivity
import com.vm.eea.domain.be

class AddNewDefaultSoilResistivity(private val repository: IDefaultSoilResistivityRepository) {

   suspend  operator fun invoke(value:Number, unitOfThermalResistivity: UnitOfThermalResistivity){
        val temp= value be unitOfThermalResistivity
       val item= DefaultSoilResistivity(temp,true,0)
       repository.insert(item)
    }

}