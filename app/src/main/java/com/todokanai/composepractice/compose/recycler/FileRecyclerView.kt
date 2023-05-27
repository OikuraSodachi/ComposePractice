package com.todokanai.composepractice.compose.recycler

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.todokanai.composepractice.compose.holder.FileHolder
import com.todokanai.composepractice.viewmodel.FileListViewModel

@Composable
fun FileRecyclerView(viewModel:FileListViewModel) {

    val context = LocalContext.current
    val fileList = viewModel.sortedFileList.collectAsStateWithLifecycle()

    LazyColumn {
        items(fileList.value.size) {
            val file = fileList.value[it]
            Box(
                modifier = Modifier
                    .clickable {viewModel.onItemClick(context,file.file) }
                        //    onPress = {viewModel.onItemLongClick(file)}
            ) {
                FileHolder(file)
            }   // 뷰홀더 역할 부분
        }
    }
}



@Preview
@Composable
fun FileRecyclerViewPreview(){
    FileRecyclerViewPreview()
}