package com.vm.eea.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun FullPageDialog(pageTitle:String,
                   canSubmit:Boolean=false,
                   onClose:()->Unit={},onSubmit:(()->Unit)?=null,
                   pageContent:@Composable ()->Unit){


    Scaffold(Modifier.fillMaxSize(), topBar = {    TopAppBar(
        elevation = 0.dp,
        title = {},
        //backgroundColor =  MaterialTheme.colors.primarySurface,
        backgroundColor =  Color.Transparent,
        navigationIcon = {
            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, null)
            }
        },actions = {
            if(onSubmit!=null){
                IconButton(onClick = onSubmit,enabled = canSubmit) {
                    Icon(Icons.Filled.Check, null)
                }
            }

        })
    }) {
        Surface(modifier = Modifier.fillMaxSize()
           // ,color = MaterialTheme.colors.primarySurface
        ) {
            Column(Modifier.fillMaxSize()) {
                Text(text = pageTitle,modifier= Modifier.padding(top=0.dp,start=16.dp,bottom = 16.dp),
                    style = MaterialTheme.typography.h4)
                pageContent()
            }

        }
    }
}


@Composable
fun Page1(pageTitle:String,
          actions:@Composable RowScope.() -> Unit = {},
          floatingActionButton:@Composable ()->Unit={},
          floatingActionButtonPosition:FabPosition=FabPosition.Center,
          pageContent:@Composable ()->Unit){
    Scaffold(
        Modifier.fillMaxSize(),
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                title = { Text(text = pageTitle) },
                actions = actions,
                // backgroundColor =  MaterialTheme.colors.primarySurface,
                backgroundColor =  Color.Transparent,
                navigationIcon = {
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                })
        },
    ) {
        Surface(modifier = Modifier.fillMaxSize(),
          // color = MaterialTheme.colors.primarySurface
        ) {
          Column() {
//                Text(text = pageTitle,modifier=Modifier.padding(top=0.dp,start=16.dp,bottom = 16.dp),
//                    style = MaterialTheme.typography.h4)
             //   TitleComponent(title = pageTitle)
                pageContent()

            }


        }
    }
}

@Composable
fun BottomSheet(title:String,modifier: Modifier=Modifier,content:@Composable ()->Unit){
    Column(modifier) {
        Text(text = title,
            style=MaterialTheme.typography.h6,textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp,
                start = 32.dp,
                end = 16.dp,
                bottom = 16.dp))

       content()
    }
}
