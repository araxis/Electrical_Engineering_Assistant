package com.vm.eea.ui

data class PropertyItem(val Name:String,
                        val value:String,
                        val visible:Boolean=true,
                        val isCalculated:Boolean=false,
                        val category:String="",
                        val updateViewRoute:(()->INavigationCommand)? =null){

    companion object{
        fun simple(name:String,vale:String,category: String) =PropertyItem(name,vale,
            visible = true,
            isCalculated = true,
            category = category,
            updateViewRoute = null)
    }
}

data class GroupPropertyItem(val key:String,
                        val value:String,
                        val items:List<PropertyItem>,
                        val visible:Boolean=true,
                        val expanded:Boolean=true
)