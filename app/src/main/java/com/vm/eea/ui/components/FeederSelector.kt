package com.vm.eea.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vm.eea.ui.panel.updateFeeder.FeederItem
import com.vm.eea.utilities.isOdd

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeederItemSelector(feeders:List<FeederItem>, modifier: Modifier = Modifier, onSelect:(FeederItem)->Unit){

    LazyVerticalGrid(modifier=modifier,
        cells = GridCells.Fixed(2),
    ) {
        itemsIndexed(feeders){ index, it->
            val base= Modifier
            val padding=if(!index.isOdd()){
                base.padding(end = 4.dp,bottom = 8.dp)
            }else{
                base.padding(start = 4.dp,bottom = 8.dp)
            }
            FeederItemView(modifier = padding.clickable { onSelect(it) },item = it)
        }

    }
}

@Composable
fun FeederItemView(item: FeederItem, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        ,elevation = 2.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(24.dp))
            Text(text = item.feeder.code,textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f),
                style = TextStyle(
                    fontWeight = FontWeight.W700,
                    fontSize = 19.sp
                ))
            if (item.isSelected) {
                Icon(Icons.Filled.Check, contentDescription = "",modifier= Modifier.padding(start=0.dp,end=0.dp,top=16.dp))
            } else {
                Spacer(modifier = Modifier.width(24.dp))
            }

        }
    }

}
