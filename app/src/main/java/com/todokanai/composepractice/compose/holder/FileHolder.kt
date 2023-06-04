package com.todokanai.composepractice.compose.holder

import android.graphics.Bitmap
import android.icu.text.DateFormat
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.todokanai.composepractice.R
import com.todokanai.composepractice.tools.independent.FileActionModel
import java.io.File

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileHolder(file:File, onClick:()->Unit,onLongClick:()->Unit
               ) {
    val context = LocalContext.current
    val model = FileActionModel()
    val size =
        if(file.isDirectory) {
            "${file.listFiles().size} 개"
        } else {
            model.readableFileSize(file.length())
        }
    val thumbnail : Bitmap =
        when(file.extension){
            "" ->{
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_folder_24)?.toBitmap()!!
            }
            "pdf"->{
                ContextCompat.getDrawable(context, R.drawable.ic_pdf)?.toBitmap()!!
            }
            else ->{
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_insert_drive_file_24)?.toBitmap()!!
            }
        }

    val color = Color.Transparent
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color)
            .combinedClickable(
                onClick = { onClick() },
                onLongClick = { onLongClick() }
            )
    ) {
        val (fileImage) = createRefs()
        val (fileName) = createRefs()
        val (fileDate) = createRefs()
        val (fileSize) = createRefs()

        if(file.extension == "jpg") {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(file.toUri())
                    .crossfade(true)
                    .build(),
                null,
                modifier = Modifier
                    .constrainAs(fileImage) {
                        start.linkTo(parent.start)
                    }
                    .aspectRatio(1 / 1f)
                    .widthIn(50.dp)
            )
        } else {
            Image(
                thumbnail.asImageBitmap(),
                null,
                modifier = Modifier
                    .constrainAs(fileImage) {
                        start.linkTo(parent.start)
                    }
                    .width(50.dp)
            )
        }

        Text(
            size,
            fontSize = 15.sp,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(fileSize) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .wrapContentWidth()
                .padding(2.dp)
        )

        Text(
            file.name,
            fontSize = 18.sp,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(fileName) {
                    start.linkTo(fileImage.end)
                    end.linkTo(fileSize.start)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
                .height(30.dp)
                .padding(2.dp)
        )

        Text(
            DateFormat.getDateTimeInstance().format(file.lastModified()),
            fontSize = 15.sp,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(fileDate) {
                    start.linkTo(fileImage.end)
                    end.linkTo(fileSize.start)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(fileName.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .padding(2.dp)
        )
    }
}

@Preview
@Composable
fun Preview(){
    FileHolder(file = File("testpath"), onClick = { /*TODO*/ }) { /*TODO*/ }
}
