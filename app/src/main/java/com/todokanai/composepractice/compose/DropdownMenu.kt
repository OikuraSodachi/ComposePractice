package com.todokanai.composepractice.compose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MyDropdownMenu() {
    var menu_expanded by remember { mutableStateOf(false) }
    TextButton(
        onClick = { menu_expanded = !menu_expanded }
    ) {
        Text("Show Menu")
        if(menu_expanded) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                DropdownMenu(
                    expanded = menu_expanded,
                    onDismissRequest = { menu_expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Content 1") },
                        onClick = { /* Todo */ }
                    )
                    DropdownMenuItem(
                        text = { Text("Content 2") },
                        onClick = { /* Todo */ }
                    )
                }
            }
        }
    }
}


@Composable
fun Demo_DropDownMenu() {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.Black)
          //  .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More"
            )
        }       // 이 IconButton의 내용을 OptionsButton쪽으로 옮겨야할듯?
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Load") },
                onClick = { Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show() }
            )
            DropdownMenuItem(
                text = { Text("Save") },
                onClick = { Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val context = LocalContext.current
    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                coffeeDrinks.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

/*
@Composable
fun InteractionSample() {
    val interactionSource = MutableInteractionSource()
    val coroutineScope = rememberCoroutineScope()

    val onClick: () -> Unit = {
        Log.d("TAG", "I want to receive the clicks from the red box")
    }

    Box {
        Box(
            Modifier
                .size(200.dp)
                .background(Color.Blue)
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple(),
                    onClick = onClick
                )
        ) {}
        Box(
            Modifier
                .size(200.dp)
                .offset(x = 190.dp)
                .alpha(0.2F)
                .background(Color.Red)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            Log.d("TAG", "I want my clicks to pass through to the blue box")
                            coroutineScope.launch {
                                val press = PressInteraction.Press(it)
                                interactionSource.emit(
                                    press
                                )
                                interactionSource.emit(
                                    PressInteraction.Release(press)
                                )
                            }
                            onClick()
                        }
                    )
                }
        ) {}
    }

}

 */
@Preview
@Composable
fun MyDropdownMenuPreview(){
    Demo_ExposedDropdownMenuBox()

}