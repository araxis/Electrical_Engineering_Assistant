package com.vm.eea.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vm.eea.R
import com.vm.eea.ui.MenuItem

@Composable
fun EmptyContent(modifier: Modifier=Modifier,emptyAction:()->Unit){
    Column( modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painterResource(R.drawable.emp3),"content description")
        IconButton(onClick = emptyAction){
            Icon(Icons.Filled.Add,contentDescription = "Localized description")

        }

    }

}

@Composable
fun EmptyContent2(modifier: Modifier=Modifier){
    Column( modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painterResource(R.drawable.emp3),"content description")
    }

}

@Composable
fun ActionItem(@DrawableRes iconRes:Int,text:String, modifier: Modifier=Modifier, action:()->Unit){
  Box(modifier = modifier){
      Row( modifier =Modifier.fillMaxWidth().padding(8.dp).clickable { action() },verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Start) {
          Icon(modifier=Modifier.padding(8.dp),
              painter = painterResource(iconRes),
              contentDescription = null // decorative element
          )
          Text(modifier=Modifier.padding(8.dp),text =text )

      }
  }


}

@Composable
fun LoadingContent(modifier: Modifier=Modifier,
                   loadingContent:@Composable ()->Unit= {Text(text = "loading...")}){
    Column(modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        CircularProgressIndicator()
        loadingContent()
    }
}

@Composable
fun LabelledCheckbox(isChecked:Boolean,
                     label:String,
                     modifier: Modifier=Modifier,
                     enabled:Boolean=true,
                     onValueChange:(Boolean)->Unit) {
    val clickableModifier=if(enabled) modifier.clickable { onValueChange(!isChecked) }
    else{
        modifier
    }
    Row(modifier=clickableModifier) {


        Checkbox(
            checked = isChecked,
            onCheckedChange =onValueChange,
            enabled = enabled
        )
        Text(text = label)
    }
}









@Composable
fun IconItem(first:@Composable ()->Unit, second:@Composable ()->Unit, modifier: Modifier=Modifier){
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically) {
        first()
        second()
    }
}

@Composable
fun NameValueRowItem(nameContent:@Composable ()->Unit, valueContent:@Composable ()->Unit,
                     modifier: Modifier =Modifier,
){
   Surface(modifier =modifier) {
       Row(modifier =Modifier.padding(8.dp)
           ,horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically) {

           nameContent()

           valueContent()


       }
   }

}

@Composable
fun FormItemSelector(name:String,value:String,
                     modifier: Modifier =Modifier,onClick:()->Unit
){
    Card(modifier = Modifier
        .padding(top = 8.dp)
        .clickable { onClick() },
        border = BorderStroke(1.dp,  MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled))){

        Box(modifier = modifier) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
                ,horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                IconItem(first = {


                },second = {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text =name,
                        color=LocalContentColor.current.copy(LocalContentAlpha.current),
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(start = 8.dp))}
                })

                IconItem(first = {
                    Text(text =value, modifier = Modifier
                        .padding(end = 8.dp))
                },
                    second = {

                        Icon(imageVector = Icons.Filled.NavigateNext,
                            "")


                    })


            }
        }
    }

}

@Composable
fun FormItemDropSelector(name:String,value:String,
                     modifier: Modifier =Modifier,onClick:()->Unit
){
    Card(modifier = Modifier
        .padding(top = 8.dp)
        .clickable { onClick() },
        border = BorderStroke(1.dp,  MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled))){

        Box(modifier = modifier) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
                ,horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                IconItem(first = {


                },second = {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(text =name,
                            color=LocalContentColor.current.copy(LocalContentAlpha.current),
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(start = 8.dp))}
                })

                IconItem(first = {
                    Text(text =value, modifier = Modifier
                        .padding(end = 8.dp))
                },
                    second = {

                        Icon(imageVector = Icons.Filled.ArrowDropDown,
                            "")


                    })


            }
        }
    }

}



@Composable
fun SymbolValue(symbol:String, value:String, modifier: Modifier=Modifier,
                color:Color=MaterialTheme.colors.primary){

    Box(modifier = modifier){
        Row(modifier =Modifier.fillMaxWidth()
            ,horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {

            Surface(modifier= Modifier.defaultMinSize(50.dp),
                shape = RoundedCornerShape(2.dp),
                border= BorderStroke(1.dp, Color.Transparent),
               // contentPadding = PaddingValues(2.dp),  //avoid the little icon
                color = color
            ) {
                //Icon(Icons.Default.Search, contentDescription = "")
                Text(text = symbol,textAlign = TextAlign.Center,fontWeight = FontWeight.Bold,modifier = Modifier.padding(4.dp))
            }

            Text(text = value,modifier = Modifier.padding(start = 8.dp))


        }
    }





}

@Composable
fun TileView(symbol:String, value:String,
             modifier: Modifier=Modifier,
             headerBackColor:Color=MaterialTheme.colors.primary,
             contentBackColor:Color=MaterialTheme.colors.secondary){
    Card(modifier = modifier,elevation = 0.dp) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)) {
            Surface(color = headerBackColor) {
                Text(symbol,textAlign = TextAlign.Center, style = TextStyle(
                    fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W900,
                    fontSize = 16.sp
                ), modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                )
            }
Surface(color = contentBackColor) {
    Text(
        value,textAlign = TextAlign.Center, style = TextStyle(
            fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W400,
            fontSize = 14.sp
        ), modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
}
}

    }
}

@Composable
fun TileView(value:MenuItem,
             modifier: Modifier=Modifier,
             backColor:Color=MaterialTheme.colors.primary,
             onClick: (MenuItem) -> Unit
            ){

            Surface(modifier = modifier,color = backColor) {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onClick(value) }) {
                    Text(
                        value.title, textAlign = TextAlign.Center, style = TextStyle(
                            fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W400,
                            fontSize = 14.sp
                        ), modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }

            }



}

@Composable
fun TileView(value:String,
             modifier: Modifier=Modifier,
             backColor:Color=MaterialTheme.colors.primary,
             onClick: () -> Unit
){

        Surface(modifier = modifier,color = backColor) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onClick() }) {
                Text(
                    value, textAlign = TextAlign.Center, style = TextStyle(
                        fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W400,
                        fontSize = 14.sp
                    ), modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }

        }


}

@ExperimentalAnimationApi
@Composable
fun ExpandableContent(header:@Composable ()->Unit, modifier: Modifier=Modifier, footer:(@Composable ()->Unit)?=null, openedHeader:(@Composable ()->Unit)?=null, body:@Composable () -> Unit){
    var expanded by remember{ mutableStateOf(false)}

    Column(modifier = modifier) {
             Row(Modifier.fillMaxWidth(),Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(1f)){
                    if(expanded){
                        if(openedHeader!=null){
                            openedHeader()
                        }else{
                            header()
                        }
                    }else{
                        header()
                    }
                }

                IconButton(onClick = { expanded=!expanded },Modifier.wrapContentSize()) {
                    if(expanded){
                        Icon(imageVector = Icons.Default.ArrowDropUp, contentDescription = "")
                    }else{
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                    }

                }
            }

           AnimatedVisibility(expanded,modifier=Modifier.fillMaxWidth()){

                    body()

            }

            if(footer!=null){
                Box(modifier = Modifier.fillMaxWidth()) {
                    footer()
                }
            }

        }






}
