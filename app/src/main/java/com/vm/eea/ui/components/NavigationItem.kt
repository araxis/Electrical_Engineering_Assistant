package com.vm.eea.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vm.eea.ui.GroupPropertyItem
import com.vm.eea.ui.MenuItem
import com.vm.eea.ui.PropertyItem

@Composable
fun PropertyList(items:List<PropertyItem>, onItemClick:(PropertyItem)->Unit){
    var baseModifier=Modifier.fillMaxWidth()
    LazyColumn(Modifier.fillMaxSize()){
        itemsIndexed(items){ _, item->
            if(item.visible) {
                if(!item.isCalculated) baseModifier=baseModifier.clickable { onItemClick(item) }
                Surface(modifier = baseModifier) {
                    PropertyItemView(item, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
                }
            }

        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GroupPropertyList(list:List<GroupPropertyItem>){

    val hiddenKeys = remember {  mutableStateListOf<String>() }
    val baseModifier=Modifier.fillMaxWidth()
    LazyColumn(Modifier.fillMaxSize()){
        items(list){
            Surface(modifier = baseModifier.clickable {
                if(hiddenKeys.contains(it.key)){
                    hiddenKeys.remove(it.key)
                }else{
                    hiddenKeys.add(it.key)
                }
            }) {
                NameValueRowItem(modifier=baseModifier,nameContent={
                    IconItem(first = {},second = {
                        Text(text = it.value,style = TextStyle.Default.copy(fontSize = 22.sp),
                            modifier = Modifier.padding(start = 8.dp))
                    })
                },valueContent={IconItem(first = {},
                    second = {
                      if(hiddenKeys.contains(it.key)){
                          Icon(imageVector = Icons.Default.ExpandMore, contentDescription = "")
                      }else{
                          Icon(imageVector = Icons.Default.ExpandLess, contentDescription = "")
                      }
                    })
                })
            }
               if(!hiddenKeys.contains(it.key)){
                   it.items.forEach {item->
                       NameValueRowItem(modifier = Modifier.fillMaxWidth(),nameContent={
                           IconItem(first = {},second = {
                               Text(text = item.Name,modifier = Modifier.padding(start = 8.dp))
                           })
                       },valueContent={
                           IconItem(first = {
                               Text(text = item.value, modifier = Modifier
                                   .padding(end = 8.dp))
                           },
                               second = {})
                       })
                   }
               }



        }

    }
}



@Composable
fun MenuList(items:List<MenuItem>,modifier: Modifier=Modifier, onItemClick:(MenuItem)->Unit){

    LazyColumn(modifier){
        itemsIndexed(items){ _, item->

            val baseModifier= Modifier
                .fillMaxWidth()
                .clickable { onItemClick(item) }
                Surface(modifier = baseModifier) {
                    MenuItemView(item, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
                }


        }
    }
}

@Composable
fun PropertyItemView(item: PropertyItem,modifier: Modifier = Modifier,
){


    NameValueRowItem(modifier=modifier,nameContent={
        IconItem(first = {
          if(item.isCalculated)Icon(imageVector = Icons.Filled.Calculate,
              "",tint = MaterialTheme.colors.primary)

        },second = {
            Text(text = item.Name,modifier = Modifier.padding(start = 8.dp))
        })
    },valueContent={
        IconItem(first = {
            Text(text = item.value, modifier = Modifier
                .padding(end = 8.dp))
        },
            second = {
                if(!item.isCalculated)Icon(imageVector = Icons.Filled.NavigateNext,"")
            })
    })

}

@Composable
fun MenuItemView(item: MenuItem,
                     modifier: Modifier = Modifier,
){


    NameValueRowItem(modifier=modifier,nameContent={
        IconItem(first = {

        },second = {
            Text(text = item.title,modifier = Modifier.padding(start = 8.dp))
        })
    },valueContent={
        IconItem(first = {
//            Text(text = item.value, modifier = Modifier
//                .padding(end = 8.dp))
        },
            second = {
                Icon(imageVector = Icons.Filled.NavigateNext,"")


            })
    })

}