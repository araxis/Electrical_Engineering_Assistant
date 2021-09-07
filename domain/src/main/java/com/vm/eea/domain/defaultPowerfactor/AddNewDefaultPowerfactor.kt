package com.vm.eea.domain.defaultPowerfactor

import com.vm.eea.domain.DefaultPowerfactor
import com.vm.eea.domain.PowerFactor
import com.vm.eea.domain.PowerSystem

class AddNewDefaultPowerfactor(private val repository: IDefaultPowerfactorRepository) {

    suspend fun invoke(value:Number,system: PowerSystem){
        val isExist=repository.isExist(value.toDouble(),system)
        if(isExist) return
        val item= DefaultPowerfactor(PowerFactor(value),system,true,0)
        repository.insert(item)
    }
    suspend operator fun invoke(item: DefaultPowerfactor){
        val isExist=repository.isExist(item.value.value,item.system)
        if(isExist) return
        repository.insert(item)
    }
}