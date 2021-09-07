package com.vm.eea.ui.models

import com.vm.eea.domain.LoadId
import com.vm.eea.domain.project.ProjectId

data class SimpleMotor(val projectId: ProjectId, val loadId: LoadId, val code:String, val description:String)
