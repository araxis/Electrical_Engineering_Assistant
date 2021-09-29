package com.vm.eea.application.project

import com.vm.eea.application.SelectableItem
import com.vm.eea.application.WireSize
import com.vm.eea.application.WireSizeType

interface IGetProjectWireSize {

    suspend operator fun invoke(projectId: ProjectId,type:WireSizeType):List<SelectableItem<WireSize>>
}