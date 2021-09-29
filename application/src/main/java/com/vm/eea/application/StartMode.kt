package com.vm.eea.application

enum class StartMode {
    Dol,Sd,SSd,Vsd;

    override fun toString(): String {
        return name
    }
}