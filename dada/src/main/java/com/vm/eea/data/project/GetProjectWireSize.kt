package com.vm.eea.data.project

import com.vm.eea.application.SelectableItem
import com.vm.eea.application.UnitOfWireSize
import com.vm.eea.application.WireSize
import com.vm.eea.application.WireSizeType
import com.vm.eea.application.project.IGetProjectWireSize
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetProjectWireSize(private val db:AppDatabase): IGetProjectWireSize {
    override suspend fun invoke(projectId: ProjectId, type: WireSizeType): List<SelectableItem<WireSize>> {
        val current=when(type){
            WireSizeType.Max -> db.projectReadDao().getMaxWireSize(projectId.id)
            WireSizeType.Min -> db.projectReadDao().getMinWireSize(projectId.id)
        }

        val defaults= listOf(1.5,2.5,4,6,10,16,25,35,50,70,95,120,150,185,240,300)
        return defaults.map { SelectableItem(WireSize(it,UnitOfWireSize.MM2),isSelected = it==current) }
    }
}