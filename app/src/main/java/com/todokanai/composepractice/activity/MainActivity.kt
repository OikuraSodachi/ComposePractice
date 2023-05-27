package com.todokanai.composepractice.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.asLiveData
import com.todokanai.composepractice.compose.MyBackGround
import com.todokanai.composepractice.ui.theme.ComposePracticeTheme
import com.todokanai.composepractice.viewmodel.FileListViewModel
import com.todokanai.composepractice.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var activityResult: ActivityResultLauncher<String>
    private val viewModel: MainViewModel by viewModels()
    private val fViewModel: FileListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MyBackGround(fViewModel)
                }
            }
        }
        if (!viewModel.checkPermission(this)) {
            viewModel.requestPermission(this)
        }

        activityResult =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (!isGranted)
                    finish()
            }

        viewModel.requestStorageManageAccess(this)

        onBackPressedDispatcher.addCallback() {
            fViewModel.onBackPressed()
        }

        fViewModel.currentPath.asLiveData().observe(this){
            println("path: $it")
            fViewModel.updateContents()
        }
    }
}

@Preview
@Composable
fun Preview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        ) {
        MyBackGround(FileListViewModel())
    }
}