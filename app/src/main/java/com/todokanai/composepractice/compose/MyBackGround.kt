package com.todokanai.composepractice.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.todokanai.composepractice.compose.frag.FileListFrag
import com.todokanai.composepractice.compose.frag.OptionFrag
import com.todokanai.composepractice.viewmodel.FileListViewModel

@Composable
fun MyBackGround(viewModel:FileListViewModel){
    ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (optionsContainerView) = createRefs()
        val (fileListContainerView) = createRefs()
        Box(modifier = Modifier
                .height(70.dp)
            .fillMaxWidth()
                .constrainAs(optionsContainerView) {
                    top.linkTo(parent.top)
                }
        ) {
            OptionFrag(viewModel)
        }
        Box(modifier = Modifier
                .constrainAs(fileListContainerView) {
                    top.linkTo(optionsContainerView.bottom)
                    end.linkTo(parent.end)
                    Dimension.fillToConstraints
                }
            .fillMaxWidth()
        ) {
            FileListFrag(viewModel)
        }
    }
}

@Preview
@Composable
fun ContainerPreview(){
    MyBackGround(FileListViewModel())
}