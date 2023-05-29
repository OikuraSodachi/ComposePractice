package com.todokanai.composepractice.viewmodel

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todokanai.composepractice.dataclass.FileItem
import com.todokanai.composepractice.myobjects.Constants
import com.todokanai.composepractice.myobjects.Constants.CONFIRM_MODE
import com.todokanai.composepractice.myobjects.Constants.DEFAULT_MODE
import com.todokanai.composepractice.myobjects.Constants.MULTI_SELECT_MODE
import com.todokanai.composepractice.tools.FileAction
import com.todokanai.composepractice.tools.FileProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Path
import javax.inject.Inject

@HiltViewModel
class FileListViewModel @Inject constructor():ViewModel(){

    private val _sortedFileList = MutableStateFlow<List<FileItem>>(emptyList())
    val sortedFileList : StateFlow<List<FileItem>>
        get() = _sortedFileList

    private val _nsortedFileList = MutableStateFlow<List<File>>(emptyList())
    val nsortedFileList : StateFlow<List<File>>
        get() = _nsortedFileList


    private val _selectMode = MutableStateFlow<Int>(Constants.DEFAULT_MODE)      // selectMode 원본
    val selectMode : StateFlow<Int>
        get() = _selectMode

    private val _currentPath = MutableStateFlow<Path>(Environment.getExternalStorageDirectory().toPath())
  //  private val _currentPath = MutableStateFlow<Path>(Path("/storage/emulated/0"))
    val currentPath : StateFlow<Path>
        get() = _currentPath
    // currentPath 값이 변경되면 자동으로 updateContents 작동

    private val _dirTree = MutableStateFlow<List<Path>>(emptyList())
    val dirTree : StateFlow<List<Path>>
        get() = _dirTree

    private val _selectedList = MutableStateFlow<MutableSet<File>>(mutableSetOf<File>())
    val selectedList : StateFlow<Set<File>>
        get() = _selectedList

    private val _showBottomButton = MutableStateFlow<Boolean>(false)
    val showBottomButton : StateFlow<Boolean>
        get() = _showBottomButton

    var selectedItem : File? = null

    private val fAction = FileAction()
    fun updateContents() {
        viewModelScope.launch {
            val fileList = (_currentPath.value.toFile().listFiles() as Array<File>).sortedBy { it.name }
            _nsortedFileList.value = fileList
            _dirTree.value = dirTree(_currentPath.value)


        }
    }





    fun onDirectoryClick(dirName:Path){
        _currentPath.value = dirName
    }


    private fun dirTree(currentPath:Path): List<Path> {
        val result = mutableListOf<Path>()
        var now = currentPath
        while (now.parent != null) {
            result.add(now)
            now = now.parent
        }
        return result.reversed()
    }

    fun onItemClick(context: Context, selectedFile: File){
        selectedItem = selectedFile
        when(_selectMode.value){
            DEFAULT_MODE -> {
                fAction.openAction(context,_currentPath,selectedFile)
            }
            MULTI_SELECT_MODE -> {
                selectedItem = selectedFile
                updateSelectedList(_selectedList.value,selectedFile)
            }
            CONFIRM_MODE -> {
                if(selectedFile.isDirectory) {
                    fAction.openAction(context,_currentPath,selectedFile)
                }
            }
        }
    }

    fun onBackPressed(){
        when(_selectMode.value){
            DEFAULT_MODE -> {
                toParentDirectory()
            }
            MULTI_SELECT_MODE -> {
                _selectMode.value = DEFAULT_MODE
            }
            CONFIRM_MODE -> {
                toParentDirectory()
            }
        }
    }

    fun onItemLongClick(selectedFile: File){
        selectedItem = selectedFile
        when(_selectMode.value){
            DEFAULT_MODE -> {
                _selectMode.value = MULTI_SELECT_MODE
                _selectedList.value.add(selectedFile)
            }
            MULTI_SELECT_MODE -> {

            }
            CONFIRM_MODE -> {

            }
        }
    }

    private fun toParentDirectory(){
        updateDirectory(_currentPath.value.parent)
    }

    private fun updateDirectory(path:Path){
        _currentPath.value = path
    }


    private fun updateSelectedList(selectedList:MutableSet<File>, selectedFile: File){
        if(selectedList.contains(selectedFile)){
            selectedList.remove(selectedFile)
        } else{
            selectedList.add(selectedFile)
        }
        Log.d("FileAction","selectedList: $selectedList")
    }           // 선택 목록 selectedList에 대해서 추가/제거 동작


    private suspend fun File.toFileItem(file: File):FileItem = withContext(Dispatchers.Main){ FileProperty(file).fileItem()}
           // FileItem으로 변환





}