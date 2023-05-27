package com.todokanai.composepractice.compose.recycler

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.todokanai.composepractice.compose.holder.DirectoryHolder
import com.todokanai.composepractice.viewmodel.FileListViewModel

@Composable
fun DirectoryRecycler(viewModel:FileListViewModel){

    val items = viewModel.dirTree.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .height(30.dp)
            .fillMaxWidth()
            .clickable{

            }
       //     .background(Color.Black)
    ) {
        LazyRow() {
            items(items.value.size){
                DirectoryHolder(path = items.value[it], onClick = {viewModel.onDirectoryClick(items.value[it])})

            }
        }
    }
}



@Preview
@Composable
fun DirectoryRecyclerPreview(){
    DirectoryRecycler(FileListViewModel())
}
