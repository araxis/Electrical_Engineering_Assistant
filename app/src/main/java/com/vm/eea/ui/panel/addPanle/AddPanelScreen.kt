package com.vm.eea.ui.panel.addPanle

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vm.eea.domain.UnitOfLength
import com.vm.eea.domain.panel.Panel
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.utilities.IText
import com.vm.eea.utilities.isOdd

@Composable
fun AddPanelScreen(viewModel: AddPanelViewModel ) {
    val state by viewModel.container.stateFlow.collectAsState()


    FullPageDialog(pageTitle = "New Panel",canSubmit = state.canSubmit,
        onSubmit = {viewModel.onSubmit()}) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            AddPanelForm(state.feeder,state.feeders,state.code,
                state.description,state.length,
                codeError = state.codeError,
                onFeederSelect={viewModel.onChangeFeeder(it)},
                onDescriptionChange = { viewModel.onDescriptionChange(it) },
                onLengthChange={s,u->viewModel.onLengthChange(s,u)},
                onCodeChange = { viewModel.onCodeChange(it) })
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddPanelForm(currentPanel:Panel?, feeders: List<Panel>, code: String,
                 description: String,length:String,
                 modifier: Modifier = Modifier,
                 codeError: IText? = null,
                 lengthError:IText?=null,
                 onFeederSelect:(Panel)->Unit,
                 onCodeChange: (String) -> Unit,
                 onLengthChange:(String,UnitOfLength)->Unit,
                 onDescriptionChange:(String)->Unit,
){
    Card(modifier = modifier) {
        Column(Modifier.wrapContentHeight()) {

            OutlinedTextField(value = code,
                label= { Text(text = "Code") },
                maxLines=1,
                singleLine = true,
                isError = codeError!=null,
                placeholder={ Text(text = "enter project code") },
                onValueChange = {
                    onCodeChange(it)
                },modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp))
            codeError?.let {
                Text(text = it.text(LocalContext.current),
                    color= MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            }

            OutlinedTextField(value = description,
                label={ Text(text = "Description") },
                placeholder={ Text(text = "enter project description") },
                onValueChange ={
                    onDescriptionChange(it)
                },modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp))
            OutlinedTextField(value = length,
                label= { Text(text = "Length") },
                maxLines=1,
                singleLine = true,
                trailingIcon = { Text(text = "m")},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                isError = codeError!=null,
                placeholder={ Text(text = "enter length") },
                onValueChange = {
                    onLengthChange(it,UnitOfLength.M)
                },modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp))
            lengthError?.let {
                Text(text = it.text(LocalContext.current),
                    color= MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            }
//            Card(elevation = 2.dp,modifier = Modifier
//                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
//                .clickable { onFeederSelectRequest() }
//                .border(1.dp, Color.LightGray)
//                .fillMaxWidth()
//                ) {
            Text(text = "Feeder",modifier = Modifier.padding(top = 16.dp, start = 32.dp, end = 16.dp, bottom = 16.dp))
                FeederSelector(currentPanel,feeders,
                    modifier=Modifier.padding(top=8.dp,start = 16.dp,end=16.dp,bottom = 16.dp),
                    onSelect ={onFeederSelect(it)} )
           // }



        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeederSelector(currentPanel:Panel?,feeders:List<Panel>,modifier:Modifier=Modifier,onSelect:(Panel)->Unit){

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
            PanelItem(modifier = padding.clickable { onSelect(it) },item = it,selected = it.id==currentPanel?.id)
        }

    }
}

@Composable
fun PanelItem(item: Panel, modifier: Modifier = Modifier,selected:Boolean=false) {
    Card(modifier = modifier
        ,elevation = 2.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(24.dp))
            Text(text = item.code,textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f),
                style = TextStyle(
                    fontWeight = FontWeight.W700,
                    fontSize = 19.sp
                ))
            if (selected) {
                Icon(Icons.Filled.Check, contentDescription = "",modifier= Modifier.padding(start=0.dp,end=0.dp,top=16.dp))
            } else {
                Spacer(modifier = Modifier.width(24.dp))
            }

        }
    }

}




@Preview(showBackground = true)
@Composable
fun AddPanelFormPreview(
){
//    AddPanelForm("","",codeError = null,onCodeChange = {},
//        feederName = "Mdp",onFeederSelectRequest = {},
//        onDescriptionChange= {},onValueChange = {s,u->})
}
