package com.todokanai.composepractice.compose.frag

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.todokanai.composepractice.compose.recycler.FileRecyclerView
import com.todokanai.composepractice.viewmodel.FileListViewModel

@Composable
fun FileListFrag(viewModel:FileListViewModel){

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (fileRecyclerView) = createRefs()
        val (bottomButtons) = createRefs()
        FileRecyclerView(viewModel)

    }
}

@Preview
@Composable
fun FileListFragPreview(){
    FileListFrag(FileListViewModel())
}
