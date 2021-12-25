package com.vm.eea.application.project

import com.vm.eea.application.Current

data class SimpleProject(val id: ProjectId, val code:String, val description:String, val current: Current)