package com.vm.eea.application.project

import com.vm.eea.application.Length



interface IGetProjectAltitude {

   suspend  operator fun invoke(projectId: ProjectId): Length


}