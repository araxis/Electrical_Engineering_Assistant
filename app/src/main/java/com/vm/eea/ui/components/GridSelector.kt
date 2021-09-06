package com.vm.eea.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
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
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.isOdd


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun<T> GridSelector(items:List<SelectableItem<T>>, modifier: Modifier = Modifier,cols:Int=2,
                    render: (@Composable (T) -> Unit)? = null,
                    onSelect:(T)->Unit){


    LazyVerticalGrid(modifier=modifier,
        cells = GridCells.Fixed(cols),
    ) {
        itemsIndexed(items){ index, it->
            val base= Modifier.clickable { onSelect(it.value) }
            val padding=if(!index.isOdd()){
                base.padding(end = 4.dp,bottom = 8.dp)
            }else{
                base.padding(start = 4.dp,bottom = 8.dp)
            }
            SelectableItemView(modifier = padding,item = it,render = render)
        }

    }

}

@ExperimentalFoundationApi
@Composable
fun<T> GridSelector2(items:List<SelectableItem<T>>, modifier: Modifier = Modifier,cols:Int=2,
                    render: (@Composable (T) -> Unit)? = null,
                    onSelect:(T)->Unit){


    LazyVerticalGrid(modifier=modifier,
        cells = GridCells.Fixed(cols),
    ) {
        itemsIndexed(items){ _, it->
            RowItem(item = it,render = render,
                modifier = Modifier.width(150.dp).aspectRatio(2f)
                    .clickable { onSelect(it.value) }.padding(start = 8.dp))
        }

    }

}


@Composable
fun<T> RowSelector(items:List<SelectableItem<T>>, modifier: Modifier = Modifier,
                   render: (@Composable (T) -> Unit)? = null,
                   onSelect:(T)->Unit){

    LazyRow(modifier = modifier){
        items(items){
            RowItem(item = it,render = render,
                modifier = Modifier.width(150.dp).aspectRatio(2f)
                    .clickable { onSelect(it.value) }.padding(start = 8.dp))
        }

    }

}

@Composable
fun<T> RowItem( item: SelectableItem<T>, modifier: Modifier = Modifier,render: (@Composable (T) -> Unit)? = null){
    Card(modifier=modifier,
        border = BorderStroke(1.dp,  MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)),
    ) {
        Box(modifier = Modifier.padding(16.dp),contentAlignment = Alignment.Center){
            if(render!=null){
                render(item.value)
            }else{
                Text(text = item.value.toString(),textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontWeight = FontWeight.W700,
                        fontSize = 19.sp
                    ))
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
        ,elevation = if(item.isSelected) 10.dp else 4.dp) {
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

