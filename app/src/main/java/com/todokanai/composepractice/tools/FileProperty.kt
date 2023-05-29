package com.todokanai.composepractice.tools

import android.icu.text.DateFormat
import com.todokanai.composepractice.dataclass.FileItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class FileProperty(val file: File) {

    private val model = FileActionModel()
    private val extension : String = file.extension
    private val name : String = file.name
    private val date: String = DateFormat.getDateTimeInstance().format(file.lastModified())
    private val size: String
        get() {
            return if (file.isDirectory) {
                file.listFiles().size.toString()
            } else {
                model.readableFileSize(file.length())
            }
        }

    /*
    fun thumbnail(): Bitmap {
        return when(file.extension){
            "jpg","gif" -> {
                //   ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)?.toBitmap()!!
                ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.absolutePath), 100, 100)
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

     */

    suspend fun fileItem() : FileItem = withContext(Dispatchers.IO) {
        FileItem(extension, name, date, size, file)
    }

}