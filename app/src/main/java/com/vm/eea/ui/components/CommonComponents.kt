package com.vm.eea.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vm.eea.R

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
fun TitleScreen(title:String, content:@Composable ()->Unit){
    Surface(color = MaterialTheme.colors.surface,
) {

        Column(Modifier
            .padding(8.dp)) {
            Text(text = title,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth())


            content()
        }

    }
}





@Composable
fun TitleComponent(title: String) {
    // Text is a predefined composable that does exactly what you'd expect it to - display text on
    // the screen. It allows you to customize its appearance using style, fontWeight, fontSize, etc.
    Text(
        title, style = TextStyle(
            fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W900,
            fontSize = 14.sp, color = Color.Black
        ), modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
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
                     modifier: Modifier =Modifier,
){
    Surface(modifier = Modifier.padding(top=8.dp),color = Color.Transparent){
        Card(modifier =modifier,elevation = 0.dp,backgroundColor = Color.Transparent, border = BorderStroke(1.dp,  MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled))
        ) {
            Row(modifier =Modifier.padding(start = 8.dp,end=8.dp,top = 16.dp,bottom = 16.dp)
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
fun FormItemSelector2(name:String,value:String,
                     modifier: Modifier =Modifier,
){
    Surface(modifier = modifier){
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            OutlinedTextField(value=name,
                maxLines = 1,
                singleLine = true,
                readOnly=true,
                trailingIcon ={
                    IconItem(first = {Text(text =value, modifier = Modifier.padding(end = 8.dp))},
                        second = {Icon(imageVector = Icons.Filled.NavigateNext,"", Modifier.padding(end = 8.dp))})
                },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {}
            )
        }
    }


}
