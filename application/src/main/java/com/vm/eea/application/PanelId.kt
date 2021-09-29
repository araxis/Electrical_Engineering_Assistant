package com.vm.eea.application

@JvmInline
value class PanelId(val id:Long){
    operator fun invoke()=id
    companion object{
        fun empty()= PanelId(-1)
    }

}
