package de.motisproject

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Search", "My Tickets", "Account")
    val icons = listOf(Icons.Filled.Search, Icons.Filled.List, Icons.Filled.AccountBox)


    var fromTextState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("Dacia Service, Timișoara"))
    }
    var toTextState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("Universitatea Româno-Americană"))
    }

    Surface(
        tonalElevation = 5.dp,
        modifier = Modifier
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth(),
        content = {
            Scaffold(
                content = {
                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Surface(
                            tonalElevation = -5.dp,
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .fillMaxWidth(),
                            content = {
                                Row(
                                    modifier = Modifier
                                        .padding(24.dp, 18.dp)
                                        .height(intrinsicSize = IntrinsicSize.Max)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
                                    ) {
                                        Spacer(modifier = Modifier.weight(1f))
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = R.drawable.marker),
                                            contentDescription = stringResource(id = R.string.from_icon),
                                            tint = colorResource(id = R.color.pink)
                                        )
                                        Divider(
                                            color = MaterialTheme.colorScheme.outlineVariant,
                                            modifier = Modifier
                                                .height(30.dp)
                                                .width(1.dp)
                                        )
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = R.drawable.marker),
                                            contentDescription = stringResource(id = R.string.from_icon),
                                            tint = colorResource(id = R.color.blue)
                                        )
                                    }
                                    Column(modifier = Modifier.padding(24.dp, 0.dp)) {
                                        TextField(
                                            modifier = Modifier.padding(0.dp),
                                            shape = TextFieldDefaults.outlinedShape,
                                            value = fromTextState,
                                            onValueChange = { fromTextState = it },
                                            label = { Text("From") },
                                            colors = TextFieldDefaults.textFieldColors(
                                                containerColor = MaterialTheme.colorScheme.surface,
                                                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                                                unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                                                focusedIndicatorColor = MaterialTheme.colorScheme.surface
                                            ),
                                            singleLine = true
                                        )
                                        Divider(
                                            color = MaterialTheme.colorScheme.outlineVariant,
                                            modifier = Modifier
                                                .height(1.dp)
                                                .fillMaxWidth()
                                        )
                                        TextField(
                                            modifier = Modifier.padding(0.dp),
                                            shape = TextFieldDefaults.outlinedShape,
                                            value = toTextState,
                                            onValueChange = { toTextState = it },
                                            label = { Text("To") },
                                            colors = TextFieldDefaults.textFieldColors(
                                                containerColor = MaterialTheme.colorScheme.surface,
                                                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                                                unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                                                focusedIndicatorColor = MaterialTheme.colorScheme.surface
                                            ),
                                            singleLine = true
                                        )
                                    }
                                }
                            }
                        )
                    }
                },
                bottomBar = {
                    NavigationBar {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = { Icon(icons[index], contentDescription = item) },
                                label = { Text(item) },
                                selected = selectedItem == index,
                                onClick = { selectedItem = index }
                            )
                        }
                    }
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Greeting("Android")
    }
}