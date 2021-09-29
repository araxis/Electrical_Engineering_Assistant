package com.vm.eea.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vm.eea.application.SelectableItem
import com.vm.eea.utilities.isOdd


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun<T> GridSelector(items:List<SelectableItem<T>>, modifier: Modifier = Modifier,
                    render: (@Composable (T) -> Unit)? = null,
                    onSelect:(SelectableItem<T>)->Unit){


    LazyVerticalGrid(modifier=modifier,
        cells = GridCells.Adaptive(160.dp),
    ) {
        itemsIndexed(items){ index, it->
            val base= Modifier
            val padding=if(!index.isOdd()){
                base.padding(end = 4.dp,bottom = 8.dp)
            }else{
                base.padding(start = 4.dp,bottom = 8.dp)
            }
            SelectableItemView(modifier = padding.clickable { onSelect(it) },item = it,render = render)
        }

    }

}






@Composable
fun<T> ColSelector(items:List<SelectableItem<T>>, modifier: Modifier = Modifier,
                   render: (@Composable (T) -> Unit),
                   onSelect:(T)->Unit){

    LazyColumn(modifier = modifier){
        items(items){
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelect(it.value) }
                .padding(16.dp)){
                render(it.value)
            }

        }

    }

}



@Composable
fun<T> SelectableItemView(
    item: SelectableItem<T>, modifier: Modifier = Modifier,

    render: (@Composable (T) -> Unit)? = null,
    ) {
    Surface(modifier = modifier
        ,elevation =2.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(24.dp))
            if(render==null){
                Text(text = item.value.toString(),textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    style = TextStyle(
                        fontWeight = FontWeight.W700,
                        fontSize = 19.sp
                    ))
            }else{
                Box(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f), Alignment.Center){
                    render(item.value)

                }
            }

            if (item.isSelected) {
                Icon(Icons.Filled.Check, contentDescription = "",modifier= Modifier.padding(start=0.dp,end=0.dp,top=16.dp))
            } else {
                Spacer(modifier = Modifier.width(24.dp))
            }
        }
    }

}

