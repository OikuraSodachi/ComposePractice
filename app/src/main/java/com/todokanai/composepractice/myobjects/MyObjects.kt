package com.todokanai.composepractice.myobjects

import kotlinx.coroutines.flow.MutableStateFlow

object MyObjects {

    val physicalStorageList = MutableStateFlow<List<String>>(emptyList())       // 물리적 외부 저장소 목록
}