package com.todokanai.composepractice.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.todokanai.composepractice.compose.dialog.BooleanDialog
import com.todokanai.composepractice.compose.dialog.EditTextDialog

@Composable
fun OptionButtons(){

    var showBooleanDialog by remember { mutableStateOf(false) }
    if(showBooleanDialog){
        BooleanDialog(title = "test", message = "test", onConfirm = { showBooleanDialog = false }, onCancel = {showBooleanDialog = false})
    }
    var showEditTextDialog by remember { mutableStateOf(false)}
    if(showEditTextDialog){
        EditTextDialog(onConfirm = {showEditTextDialog = false}, onCancel = {showEditTextDialog = false})
    }

    Box(
        modifier = Modifier
            .padding(1.dp)
    ) {         
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentSize()
            ) {
                MyDropdownMenu()
            }
            TextButton(
                onClick = { showBooleanDialog = true},
                Modifier.weight(1f)
            ) {
                Text("Show Dialog")

            }
            TextButton(
                onClick = { showEditTextDialog = true },
                Modifier.weight(1f)
            ) {
                Text("EditTextDialog")
            }
        }
    }
}

@Preview
@Composable
fun OptionButtonsPreview(){
    OptionButtons()
}