package com.todokanai.composepractice.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomButtons(){
    Box(
        modifier = Modifier
            .padding(1.dp)
           // .background(Color.Green)
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
                onClick = { /*TODO*/ },
                Modifier.weight(1f)
            ) {
                Text("2")

            }
            TextButton(
                onClick = { /*TODO*/ },
                Modifier.weight(1f)
            ) {
                Text("3")
            }
        }
    }
}

@Preview
@Composable
fun BottomButtonsPreview(){
    BottomButtons()
}