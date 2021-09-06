package com.vm.eea.domain

data class SupplyPath(val path:String){
    val idInLevel:Int get(){
        if(path=="/") return 1
       return path.split('/').last().toInt()
    }
        val level:Int= path.filter { it == '/' }.count()

    val parent:SupplyPath
        get() {
            if(path=="/") return SupplyPath("/")
            val parts=path.split('/')
            val parentPath=path.removeSuffix("/${parts.last()}")
            return SupplyPath(parentPath)
        }

        override fun toString() =path
        operator fun invoke()=path
    }

