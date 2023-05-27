package com.todokanai.composepractice.compose.frag

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.todokanai.composepractice.compose.OptionButtons
import com.todokanai.composepractice.compose.recycler.DirectoryRecycler
import com.todokanai.composepractice.viewmodel.FileListViewModel

@Composable
fun OptionFrag(viewModel:FileListViewModel){
    Column {
        OptionButtons()
        DirectoryRecycler(viewModel = viewModel)
    }
}

@Preview
@Composable
fun OptionFragPreview(){
    OptionFrag(FileListViewModel())
}