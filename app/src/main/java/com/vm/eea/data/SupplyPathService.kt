package com.vm.eea.data

import com.vm.eea.domain.SupplyPath
import com.vm.eea.domain.ISupplyPathService

class SupplyPathService(private val db:AppDatabase): ISupplyPathService {

    override suspend fun getSupplyPath(projectId: Long, pathLength: Int): List<SupplyPath> {
       return db.projectPartDao().getPathWithLength(projectId,pathLength).map { SupplyPath(it) }
    }

    override suspend fun getSupplyPath(projectId: Long,startPath: SupplyPath,pathLength: Int,): List<SupplyPath> {
        return db.projectPartDao().getPathWithLength(projectId,startPath.path,pathLength).map { SupplyPath(it) }
    }

    override suspend fun getNextSupplyPath(projectId: Long, rootSupplyPath: SupplyPath):SupplyPath {
        val levePaths=getSupplyPath(projectId,rootSupplyPath,
            rootSupplyPath.level+1)
        val id=if(levePaths.isEmpty()) 1 else levePaths.maxOf { it.idInLevel } +1
       return SupplyPath("${rootSupplyPath()}/$id")
    }


}