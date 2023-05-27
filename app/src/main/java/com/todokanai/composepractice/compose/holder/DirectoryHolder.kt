package com.todokanai.composepractice.compose.holder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.nio.file.Path
import kotlin.io.path.name

@Composable
fun DirectoryHolder(path: Path, onClick:()->Unit){
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentWidth()
            .clickable{onClick()}
    ){
        //    Image(image,null)
        Text(text = "/${path.name}",
            Modifier
                .padding(horizontal = 20.dp)
                .align(Alignment.CenterVertically)
        )
    }

}