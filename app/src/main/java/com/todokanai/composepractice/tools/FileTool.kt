package com.todokanai.composepractice.tools

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.os.Environment
import android.os.StatFs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.name

class FileTool {
    private val model = FileActionModel()

    fun getReadableTotalSize(files:List<File>) = model.readableFileSize(model.getTotalSize(files))

    fun fileSizeFormat(size: Long) = model.readableFileSize(size)

    fun dirTree(currentPath:String): List<String> {
        val result = mutableListOf<String>()
        var now = Path(currentPath)
        while (now.parent != null) {
            result.add(now.name)
            now = now.parent
        }
        return result.reversed()
    }

    suspend fun getThumbnail(file: File): Bitmap = withContext(Dispatchers.IO){ ThumbnailUtils.extractThumbnail(
        BitmapFactory.decodeFile(file.absolutePath), 100, 100)
    }

    fun getFreeSpace() : Long {
        val path = Environment.getExternalStorageDirectory().path // 외부 저장소 경로 가져오기
        return StatFs(path).availableBytes
    }
}