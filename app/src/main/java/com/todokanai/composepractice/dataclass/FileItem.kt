package com.todokanai.composepractice.dataclass

import java.io.File

data class FileItem(
    val extension: String,      // 파일 확장자
    val name : String,
    val date: String,
    val size: String,
    val file: File
)