package com.todokanai.composepractice.compose.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EditTextDialog(
    onConfirm: (String) -> Unit,
    onCancel: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(true) }
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = onCancel,
            title = { Text(text = "EditTextDialog") },
            text = {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    placeholder = {Text("Default Text")},
                    onValueChange = { text = it },
                    singleLine = true
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm(text)
                        showDialog.value = false
                    }
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onCancel()
                        showDialog.value = false
                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}

@Preview
@Composable
fun EditTextDialogPreview(){
    EditTextDialog(onConfirm = {}) {
    }
}