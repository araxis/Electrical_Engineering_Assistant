package com.vm.eea.ui.project.projectList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vm.eea.application.project.IGetProjectSimpleList
import com.vm.eea.ui.components.EmptyContent
import com.vm.eea.ui.components.LoadingContent
import com.vm.eea.ui.components.Page1
import org.koin.androidx.compose.getViewModel

@Composable
fun ProjectListScreen(viewModel: ProjectListViewModel= getViewModel()){
     val state by viewModel.container.stateFlow.collectAsState()

    val fab=@Composable{
        ExtendedFloatingActionButton(
            onClick = { viewModel.showAddView() },
            text = { Text(text = "Project")},
            icon = {Icon( Icons.Filled.Add,contentDescription = "Localized description")},

            )}

    Page1(pageTitle = "Projects",floatingActionButton = fab,floatingActionButtonPosition = FabPosition.Center) {
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
fun SimpleProjectList(projects:List<IGetProjectSimpleList.SimpleProject>,
                      modifier: Modifier=Modifier,
                      onSelect:(IGetProjectSimpleList.SimpleProject)->Unit){
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
fun SimpleProjectItem(simpleProject: IGetProjectSimpleList.SimpleProject, modifier: Modifier=Modifier){
    Surface(modifier = modifier,elevation = 0.dp) {
        Column(Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style =  SpanStyle(fontSize = 22.sp)){
                        append(simpleProject.code)
                    }
                    withStyle(style = SpanStyle(fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.DarkGray)
                    ){
                        append(" (${simpleProject.current.toFormatString(empty = "0")})")
                    }

                }
            )

            Text( text = simpleProject.description,
                style = MaterialTheme.typography.subtitle1.copy(fontStyle = FontStyle.Italic),
            )
        }
    }

}
