package com.vm.eea.data.project

import com.vm.eea.application.Current
import com.vm.eea.application.project.IGetProjectSimpleList
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProjectSimpleList(private val db:AppDatabase):IGetProjectSimpleList {
    override fun invoke(): Flow<List<IGetProjectSimpleList.SimpleProject>> {
        return db.projectReadDao().getSimpleProjects()
            .map { list->list.map { IGetProjectSimpleList.SimpleProject(ProjectId(it.id),
                it.code,it.description,Current(it.totalCurrent,Current.Unit.A)) } }
    }
}