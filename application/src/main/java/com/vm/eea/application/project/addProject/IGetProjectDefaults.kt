package com.vm.eea.application.project.addProject

interface IGetProjectDefaults {
    suspend operator fun invoke():ProjectDefaults
}
