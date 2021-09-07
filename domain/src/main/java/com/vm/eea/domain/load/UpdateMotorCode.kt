package com.vm.eea.domain.load

import com.vm.eea.domain.LoadId

class UpdateMotorCode(private val repository:IMotorRepository) {

    suspend operator fun invoke(loadId: LoadId, code:String, description:String){
            repository.updateCode(loadId,code,description)
    }
}