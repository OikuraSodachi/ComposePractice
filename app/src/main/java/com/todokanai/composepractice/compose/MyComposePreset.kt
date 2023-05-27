package com.todokanai.composepractice.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun MyTextView(
    text:String,
    textStyle:TextStyle
){
    Box(
        Modifier
            .background(Color.Magenta)
    ) {
        Box(
            Modifier
                .padding(4.dp)
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = textStyle
            )
        }
    }
}

@Composable
fun BackgroundScreen() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (child) = createRefs()
        Box(
            Modifier
                .constrainAs(child) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxSize()      // 대상을 match_constraint 로 설정
        )
    }
}       // ConstraintLayout(match_parent)

/*
@Composable
fun infoDialogCompose(context:Context,selectedList: List<File>){
    val mDialog = Dialog(context)
    val fAction = FileAction()

 //   var showDialog by remember { mutableStateOf(false) }


    val info_name = if(selectedList.size == 1){
        selectedList.first().name
    }else{
        "${selectedList.first().name}, ${selectedList.size-1} more"
    }

    val info_size = fAction.getReadableTotalSize(selectedList)

    val infoTypesAndNumber = "Todo"

    Column(
        modifier = Modifier
            .width(200.dp)
            .wrapContentHeight()
    ){
        MyTextView(text = "Name", textStyle =  TextStyle())
        MyTextView(text = info_name, textStyle = TextStyle())
        MyTextView(text = "Size", textStyle = TextStyle())
        MyTextView(text = info_size, textStyle = TextStyle())
        MyTextView(text = "TypesAndNumber", textStyle = TextStyle())
        MyTextView(text = infoTypesAndNumber, textStyle = TextStyle())
    }
}

 */

/*
@Composable
fun VerticalRecyclerView(items: List<String>) {
    LazyColumn {
        items(items.size) { item ->
            Text(text = item.toString())
        }   // 뷰홀더 역할 부분
    }
}

@Composable
fun HorizontalRecyclerView(items: List<String>) {
    LazyRow {
        items(items.size) { item ->
            Text(text = item.toString())
        }   // 뷰홀더 역할 부분
    }
}

@Composable
fun ImageView(imageResId: Int) {
    Image(
        painter = painterResource(imageResId),
        contentDescription = null, // 이미지에 대한 설명 (옵션)
        modifier = Modifier.size(200.dp) // 이미지 크기 (옵션)
    )
}

@Composable
fun BoxWithConstraintsExample(){
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        if(this.minHeight > 220.dp){
            Text(text = "Larger than 220dp")
        }
        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            text = "minHeight : ${this.minHeight}"
        )
    }
}
 */
