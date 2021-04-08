package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.theme.BasicTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                MyScreenContent()
            }

            }
    }
}
@Composable
fun MyApp(content: @Composable () -> Unit){
    BasicTheme {
        // A surface container using the 'background' color from the theme
        //MaterialTheme.colors.background
        Surface(color = Color.White) {
           content()
        }
    }
}
@Preview("MyScreen preview")
@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android #$it" }){
    val counterState =remember{ mutableStateOf(0)}
    //to place button at button of screen include new Column with fillMaxHeight modifier
    Column(modifier = Modifier.fillMaxHeight()) {
        NamesList(names, Modifier.weight(1f))
        //now introduce weight modifier

        Column(modifier = Modifier.weight(1f)) {
            for (name in names) {
                Greeting(name = name)
                Divider(color = Color.Black)
            }
            //Divider(thickness = 32.dp, color = Color.Transparent)

        }
        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }

        )
    }
}
//Always remember to return Unit because compose function emit UI
@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit){

    Button(onClick = {updateCount(count +1)},
            colors = ButtonDefaults.buttonColors(
                backgroundColor =  if(count > 5) Color.Green else Color.Blue
            )){

        Text("I've been clicked $count times")
    }
}
@Composable
fun NamesList(names: List<String>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(items = names){name ->
            Greeting(name = name)
            Divider(color= Color.White)

        }
    }
}
@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected })
    )

}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyApp {
//        Greeting("Android")
//    }
//}