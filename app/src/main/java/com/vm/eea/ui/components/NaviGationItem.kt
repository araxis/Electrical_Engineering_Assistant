package com.vm.eea.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.PropertyItem

@Composable
fun PropertyList(items:List<PropertyItem>, onItemClick:(PropertyItem)->Unit){
    LazyColumn(Modifier.fillMaxSize()){
        itemsIndexed(items){ _, item->
            if(item.visible) {
                Surface(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) }) {
                    PropertyItemView(item, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
                }
            }

        }
    }
}

@Composable
fun PropertyItemView(item: PropertyItem,
                     modifier: Modifier = Modifier,
){


    NameValueRowItem(modifier=modifier,nameContent={
        IconItem(first = {


        },second = {
            Text(text = item.Name,modifier = Modifier.padding(start = 8.dp))
        })
    },valueContent={
        IconItem(first = {
            Text(text = item.Value, modifier = Modifier
                .padding(end = 8.dp))
        },
            second = {

                Icon(imageVector = Icons.Filled.NavigateNext,
                    "")


            })
    })

}