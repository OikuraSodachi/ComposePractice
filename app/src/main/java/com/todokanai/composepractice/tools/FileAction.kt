package com.todokanai.composepractice.tools

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.todokanai.composepractice.dataclass.FileItem
import com.todokanai.composepractice.mynotification.MyNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

class FileAction() {

    private val model = FileActionModel()
    private val myNoti = MyNotification()
    private fun toast(message: String) = AppContextTool().makeShortToast(message)

    fun openAction(context:Context, currentPath: MutableStateFlow<Path>, selectedFile: File){
        if (selectedFile.isDirectory) {
            currentPath.value = selectedFile.toPath()
        } else {
            openFile(context, selectedFile)
        }
    }   // currentPath를 MutableStateFlow<Path> 대신 Path로 전달받는 쪽으로 변경할 것?



    private suspend fun copyFiles(files:List<File>, path: Path){
        val total : Long = model.getTotalSize(files)
        println("totalOut: ${model.readableFileSize(total)}")
        if(path.toFile().freeSpace<=total){
            toast("Not enough Space ()")
        } else {
            model.copyFile(
                files,
                path,
                { fileName, bytesCopied ->
                myNoti.progressNoti(
                    fileName,
                    "${(100 * bytesCopied / total).toInt()}%",
                    (100 * bytesCopied / total).toInt()
                )}
            )
        }
    }


    private suspend fun moveFiles(files:List<File>, path: Path){
        val total : Long = model.getTotalSize(files)
        println("total: $total")
        if(path.toFile().freeSpace<=total){
            toast("Not enough Space ()")
        } else {
            moveFileWrapper(files, path) { fileName, bytesCopied ->
                println("copied: $bytesCopied")

                println("ratio: ${(bytesCopied*100)/total}")
                myNoti.progressNoti(
                    fileName,
                    "${(100 * bytesCopied / total).toInt()}%",
                    (100 * bytesCopied / total).toInt()
                )
            }
        }
    }

    private suspend fun deleteFiles(files:List<File>){
        val total : Long = model.getTotalSize(files)
        model.deleteFile(files, { fileName, bytesDeleted ->
            if(total>0L) {
                myNoti.progressNoti(
                    fileName,
                    "${(100 * bytesDeleted / total).toInt()}%",
                    (100 * bytesDeleted / total).toInt()
                )
            }
        })
    }



    private suspend fun moveFileWrapper(files: List<File>, path:Path, myCallback: (fileName: String, bytesCopied: Long) -> Unit) = withContext(
        Dispatchers.IO){
        files.forEach { file ->
            val pathName = path.absolutePathString()
            if( model.physicalStorage(file) == model.physicalStorage(path.toFile()) ) {
                Files.move(Paths.get(file.absolutePath), Paths.get(pathName + "/${file.name}"))     // 같은 저장소일경우
            } else {
                model.copyFile(listOf(file),path,myCallback)
                model.deleteSingleFile(file)
            }
        }
    }
    //-------------------------

    private fun openFile(context: Context, selectedFile: File){
        try {
            val intent = Intent()
            val authority = context.packageName
            intent.action = Intent.ACTION_VIEW
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            intent.setDataAndType(
                FileProvider.getUriForFile(context,
                    "$authority.provider",
                    selectedFile
                ), model.getMimeType(selectedFile.name))
            ActivityCompat.startActivity(context, intent, null)
        } catch (e: Exception) {

            Toast.makeText(
                context.applicationContext,
                "Cannot open the file",
                Toast.LENGTH_SHORT
            ).show()
            println(e)
        }
    }
}