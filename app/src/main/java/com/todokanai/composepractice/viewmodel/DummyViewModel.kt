package com.todokanai.composepractice.viewmodel

import android.content.Context
import android.os.Environment
import androidx.lifecycle.ViewModel
import com.todokanai.composepractice.myobjects.Constants
import com.todokanai.composepractice.tools.FileAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File
import javax.inject.Inject
import kotlin.io.path.Path
import kotlin.io.path.name


@HiltViewModel
class DummyViewModel @Inject constructor() : ViewModel() {

    private fun fAction(context: Context) = FileAction()
    val fileList = MutableStateFlow<Array<File>>(emptyArray())      // 현재 파일 목록 변수 원본
    val sortedFileList = MutableStateFlow<List<File>>(emptyList())
    val selectMode = MutableStateFlow<Int>(Constants.DEFAULT_MODE)      // selectMode 원본
    var selectedItem : File? = null
    val currentPath = MutableStateFlow<String>(Environment.getExternalStorageDirectory().path)      // 현재 경로 변수 원본
    var showBottomButton = MutableStateFlow<Boolean>(false)

    fun dirTree():List<String> {
        val result = mutableListOf<String>()
        var now = Path(currentPath.value)
        while(now.parent!=null){
            result.add(now.name)
            now = now.parent
        }
        return result.reversed()
    }


//------------------------------

    fun onItemClick(context: Context, selectedFile: File){
        selectedItem = selectedFile
        when(selectMode.value){
            Constants.DEFAULT_MODE -> {
               // fAction(context).openAction(FileListViewModel.currentPath,selectedFile)
            }
            Constants.MULTI_SELECT_MODE -> {
                selectedItem = selectedFile
            //    fAction(context).updateSelectedList(selectedList.value,selectedFile)
            }
            Constants.CONFIRM_MODE -> {
                if(selectedFile.isDirectory) {
             //       fAction(context).openAction(FileListViewModel.currentPath,selectedFile)
                }
            }
        }
    }
}