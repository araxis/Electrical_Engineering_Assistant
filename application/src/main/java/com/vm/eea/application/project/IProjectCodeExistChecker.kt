package com.vm.eea.application.project

interface IProjectCodeExistChecker {
    suspend operator fun invoke(code:String):Boolean
}