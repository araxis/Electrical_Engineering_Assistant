package com.vm.eea.domain.project

import com.vm.eea.domain.Length
import com.vm.eea.domain.defaultAltitude.DefaultAltitude
import com.vm.eea.domain.defaultAltitude.IDefaultAltitudeRepository
import com.vm.eea.ITransactionProvider
import com.vm.eea.withTransaction

class UpdateProjectAltitude(
    private val transactionProvider: ITransactionProvider,
    private val projectRepository: IProjectRepository,
    private val altitudeIDefaultAltitudeRepository: IDefaultAltitudeRepository,
) {

    suspend operator fun invoke(projectId:Long,value: Length,addToDefaults:Boolean=false){

       if(addToDefaults){
           transactionProvider.withTransaction {
               projectRepository.updateAltitude(projectId,value)
               val defaultAltitude=DefaultAltitude(value,true,0)
               altitudeIDefaultAltitudeRepository.insert(defaultAltitude)
           }
       }else{
           projectRepository.updateAltitude(projectId,value)
       }



    }
}