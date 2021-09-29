package com.vm.eea.application.project

@JvmInline
value class ProjectId(val id:Long){
    operator fun invoke()=id
}
