package com.example.morejetpackcomposables

import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.morejetpackcomposables.ui.theme.MoreJetpackComposablesTheme
import java.time.Duration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoreJetpackComposablesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  //  RadioButtonDemo()
                  //  SwitchDemo()
                  //  CheckBoxDemo()
                  //  CircularProgressDemo()
                   // SmallTopAppBarExample()
//                    DemoCard()
                    DemoFloating()
                }
            }
        }
    }
}


@Composable
fun DemoBottomNav(){
    NavigationBar {

    }
}
@Composable
fun DemoFloating(){
    val context= LocalContext.current.applicationContext
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(onClick = {
            Toast.makeText(context,"you clicked the fab",Toast.LENGTH_SHORT).show()
        }, modifier = Modifier
            .padding(16.dp)
            .align(alignment = Alignment.BottomEnd)) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add note")
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoreJetpackComposablesTheme {
        Greeting("Android")
    }
}


@Composable
fun RadioButtonDemo(){
   // val ctx= LocalContext.current
    val radioOptions= listOf<String>("Pizza","Meat","Fish")
    var (selectedItem,onOptionSelected) = remember {
        mutableStateOf(radioOptions[0])
    }
    //grouping the radio buttons in one group
    Column(modifier = Modifier.selectableGroup()) {

     radioOptions.forEach {
         label-> Row(modifier = Modifier
         .fillMaxWidth()
         .height(56.dp)
         .selectable(
             selected = (selectedItem == label),
             onClick = { onOptionSelected(label) },
             role = Role.RadioButton
         )
         .padding(horizontal = 16.dp)) {
          RadioButton(modifier = Modifier.padding(end = 16.dp),
                      onClick = {(onOptionSelected(label))},
              selected = (selectedItem==label))
          Text(text = label)
         }
       }
    }

}

@Composable
fun SwitchDemo(){
    var switchOn by remember {
        mutableStateOf(false)
    }
  Row {
      Switch(checked = switchOn, onCheckedChange = {
              switchState->switchOn=switchState
      }, colors = SwitchDefaults.colors(checkedThumbColor = Color.Green, checkedTrackColor = Color.Red))
      Text(text = "Night mode")
  }
}

@Composable
fun CheckBoxDemo(){
    var checked by remember {
        mutableStateOf(true)
    }
    var ctx= LocalContext.current.applicationContext
  Row {
      Checkbox(checked = checked, onCheckedChange = {checkedStatus->checked=checkedStatus
          Toast.makeText(ctx,"you clicked switch",Toast.LENGTH_SHORT).show()})
      Text(text = "Pizza")
  }
}

@Composable
fun CircularProgressDemo(){
    Row {
        CircularProgressIndicator(modifier = Modifier.size(32.dp), color = Color.Red, strokeWidth = 6.dp)
        CircularProgressIndicator(modifier = Modifier.size(32.dp), color = Color.Red, strokeWidth = 6.dp, progress = 0.7F)
    }
}

//scaffold -core layout predefined layout provides basic structure like appbar and floating button, drawer,snackbar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBarExample() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Small Top App Bar")
                },
                navigationIcon = { IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription ="Navigation Menu" )
                    
                }},
                actions = {},
                
            )

        },
    ) { innerPadding ->
        ScrollContent(innerPadding)
    }
}

@Composable
fun <PaddingValues> ScrollContent(innerPadding: PaddingValues) {

}

@Composable
fun DemoCard(){
    Column {
        Card(elevation = CardDefaults.cardElevation(20.dp), colors = CardDefaults.cardColors(Color.Red)) {
            Text(text = "Hello cards!")
        }
    }
}