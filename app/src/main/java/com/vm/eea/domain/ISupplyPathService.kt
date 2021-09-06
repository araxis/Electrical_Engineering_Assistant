package com.vm.eea.domain

interface ISupplyPathService {

    suspend fun getSupplyPath(projectId:Long,pathLength:Int):List<SupplyPath>
    suspend fun getSupplyPath(projectId: Long, startPath:SupplyPath, pathLength:Int):List<SupplyPath>
    suspend fun getNextSupplyPath(projectId:Long,rootSupplyPath: SupplyPath):SupplyPath


}