package com.vm.eea.application.project

interface IGetProjectDefaults {
    suspend operator fun invoke(): ProjectDefaults
}
