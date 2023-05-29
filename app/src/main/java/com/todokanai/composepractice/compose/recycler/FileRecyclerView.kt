package com.todokanai.composepractice.compose.recycler

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.DateFormat
import android.media.ThumbnailUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.todokanai.composepractice.R
import com.todokanai.composepractice.compose.holder.FileHolder
import com.todokanai.composepractice.tools.FileActionModel
import com.todokanai.composepractice.viewmodel.FileListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun FileRecyclerView(viewModel:FileListViewModel) {

    val context = LocalContext.current
    val fileList = viewModel.nsortedFileList.collectAsStateWithLifecycle()
    val model = FileActionModel()

    val ic = ContextCompat.getDrawable(context, R.drawable.ic_baseline_insert_drive_file_24)?.toBitmap()!!



    suspend fun getThumbnail(file: File): Bitmap =  withContext(Dispatchers.IO){ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.absolutePath), 100, 100)}


    LazyColumn {
        items(fileList.value.size) {
            val file = fileList.value[it]
            Box(
                modifier = Modifier
                    .clickable {viewModel.onItemClick(context,file) }
                        //    onPress = {viewModel.onItemLongClick(file)}
            ) {

                val thumbnail = remember {
                    mutableStateOf(ic)
                }


                val date = DateFormat.getDateTimeInstance().format(file.lastModified())
                val size = model.readableFileSize(file.length())
                FileHolder(file.name, date,size, thumbnail.value)
                LaunchedEffect(file){
                    if(file.extension == "jpg") {
                        thumbnail.value = getThumbnail(file)
                    }
                }

            }   // 뷰홀더 역할 부분
        }
    }
}



@Preview
@Composable
fun FileRecyclerViewPreview(){
    FileRecyclerViewPreview()
}