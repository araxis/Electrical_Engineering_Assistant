package com.vm.eea.ui.project.projectList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vm.eea.domain.project.SimpleProject
import com.vm.eea.ui.components.EmptyContent
import com.vm.eea.ui.components.LoadingContent
import com.vm.eea.ui.components.Page1
import org.koin.androidx.compose.getViewModel

@Composable
fun ProjectListScreen(viewModel: ProjectListViewModel= getViewModel()){
     val state by viewModel.container.stateFlow.collectAsState()
//    Scaffold(Modifier.fillMaxSize(),
//        isFloatingActionButtonDocked = true,
//        floatingActionButtonPosition = FabPosition.Center,
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { viewModel.showAddView() },
//                content = {
//                    Icon( Icons.Filled.Add,contentDescription = "Localized description")
//                }
//            )
//        },
//        topBar = {    TopAppBar(
//            elevation = 0.dp,
//
//            title = {
//                Text("Projects")
//            },
//            //backgroundColor =  MaterialTheme.colors.primarySurface,
//            backgroundColor = Color.Transparent,
//            navigationIcon = {
//                IconButton(onClick = {/* Do Something*/ }) {
//                    Icon(Icons.Filled.ArrowBack, null)
//                }
//            }, actions = {
//                IconButton(onClick = {/* Do Something*/ }) {
//                    Icon(Icons.Filled.Share, null)
//                }
//                IconButton(onClick = {/* Do Something*/ }) {
//                    Icon(Icons.Filled.Settings, null)
//                }
//            })
//        },
//        bottomBar = {
//            BottomAppBar(
//                content = {
//                    BottomNavigation() {
//                        BottomNavigationItem(
//                            icon = {
//                                Icon(Icons.Filled.Favorite , "")
//                            },
//                            label = { Text(text = "Favorite")},
//
//                            onClick = {
//
//                            },
//                            alwaysShowLabel = false,
//                            selected = true
//                        )
//
//
//
//
//                        BottomNavigationItem(
//                            icon = {
//                                Icon(Icons.Filled.Download , "")
//                            },
//                            label = { Text(text = "Download")},
//
//                            onClick = {
//
//                            },
//                            alwaysShowLabel = false,
//                            selected = false
//                        )}})}
//
//    ) {
//        Surface(modifier = Modifier.fillMaxSize(),
//          //  color = MaterialTheme.colors.primarySurface
//        ) {
//            if(state.loading){
//                LoadingContent()
//            }else{
//                if(state.projects.isEmpty()){
//                    EmptyContent {
//                        viewModel.showAddView()
//                    }
//                }else{
//                    SimpleProjectList(projects =state.projects,
//                        modifier = Modifier.fillMaxSize()){viewModel.onItemSelect(it)}
//                }
//            }
//
//        }
//    }

    Page1(pageTitle = "Projects") {
        if(state.loading){
            LoadingContent()
        }else{
            if(state.projects.isEmpty()){
                EmptyContent {
                    viewModel.showAddView()
                }
            }else{
                SimpleProjectList(projects =state.projects,
                    modifier = Modifier.fillMaxSize()){viewModel.onItemSelect(it)}
            }
        }
    }
}

@Composable
fun SimpleProjectList(projects:List<SimpleProject>,
                      modifier: Modifier=Modifier,
                      onSelect:(SimpleProject)->Unit){
    LazyColumn(modifier = modifier){

        itemsIndexed(projects){index,item->
            if(index==0){
                SimpleProjectItem(item,modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelect(item) }
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 8.dp))
            }else{

                SimpleProjectItem(item,modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelect(item) }
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp))
            }
        }



        //Spacer(modifier = Modifier.height(20.dp))


    }
}

@Composable
fun SimpleProjectItem(simpleProject: SimpleProject,modifier: Modifier=Modifier){
    Surface(modifier = modifier,elevation = 0.dp) {
        Column(Modifier.fillMaxWidth()) {
            Text(modifier=Modifier.padding(start = 16.dp,end =16.dp,top=8.dp),
                text = simpleProject.code,
            style = MaterialTheme.typography.caption.copy(fontSize = 22.sp),
            )
            Text(modifier=Modifier.padding(start = 16.dp,end =16.dp,bottom = 8.dp),
                text = simpleProject.description,
                style = MaterialTheme.typography.subtitle1.copy(fontStyle = FontStyle.Italic),
            )
        }
    }

}