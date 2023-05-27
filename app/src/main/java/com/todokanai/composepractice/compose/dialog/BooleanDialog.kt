package com.todokanai.composepractice.compose.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BooleanDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    val showDialog = remember { mutableStateOf(true) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { onCancel() },
            title = { Text(text = title) },
            text = { Text(text = message) },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
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
fun BooleanDialogPreview(){
    BooleanDialog(title = "BooleanDialog", message = "Message", onConfirm = { /*TODO*/ }) {

    }

}