package com.todokanai.composepractice.compose.holder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
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
import coil.compose.AsyncImage
import com.todokanai.composepractice.R
import com.todokanai.composepractice.dataclass.FileItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun FileHolder(fileItem: FileItem) {

    val context = LocalContext.current


    fun thumbnail(): Bitmap {
        return when(fileItem.extension){
            "jpg","gif" -> {
                ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)?.toBitmap()!!
          //      ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(fileItem.file.absolutePath), 100, 100)
            }
            "resource/folder"->{
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_folder_24)?.toBitmap()!!
            }
            "pdf" -> {
                ContextCompat.getDrawable(context, R.drawable.ic_pdf)?.toBitmap()!!
            }
            else -> {
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_insert_drive_file_24)?.toBitmap()!!
            }
        }
    }

    suspend fun imgThumbnail():Bitmap = withContext(Dispatchers.Main){
        ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(fileItem.file.absolutePath), 100, 100)
    }




    val name = fileItem.name
    val date = fileItem.date
    val size = fileItem.size

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        val (fileImage) = createRefs()
        val (fileName) = createRefs()
        val (fileDate) = createRefs()
        val (fileSize) = createRefs()

        AsyncImage(
            thumbnail().asImageBitmap(),
            null,
            modifier = Modifier
                .constrainAs(fileImage) {
                    start.linkTo(parent.start)
                }
                .width(50.dp)
        )
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
            name,
            fontSize = 18.sp,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(fileName){
                    start.linkTo(fileImage.end)
                    end.linkTo(fileSize.start)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
                .height(30.dp)
                .padding(2.dp)
        )

        Text(
            date,
            fontSize = 15.sp,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(fileDate){
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
    val context = LocalContext.current
    val img:ImageBitmap = ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)?.toBitmap()?.asImageBitmap()!!
    //FileHolder((img,"test","test","test"))
}