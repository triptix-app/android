package de.motisproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
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
                    Search()
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchInput() {
    var fromTextState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("Dacia Service, Timișoara"))
    }
    var toTextState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("Universitatea Româno-Americană"))
    }

    Surface(
        tonalElevation = 5.dp,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier
                    .padding(horizontal = 18.dp, vertical = 14.dp)
                    .height(intrinsicSize = IntrinsicSize.Max)
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 10.dp)
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
                            .padding(vertical = 1.dp)
                    )
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.marker),
                        contentDescription = stringResource(id = R.string.to_icon),
                        tint = colorResource(id = R.color.blue)
                    )
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        TextField(
                            shape = TextFieldDefaults.outlinedShape,
                            value = fromTextState,
                            onValueChange = { fromTextState = it },
                            label = { Text("From") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Black.copy(alpha = 0.0F),
                                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                                unfocusedIndicatorColor = Color.Black.copy(alpha = 0.0F),
                                focusedIndicatorColor = Color.Black.copy(alpha = 0.0F)
                            ),
                            singleLine = true
                        )
                        Divider(
                            color = MaterialTheme.colorScheme.outlineVariant,
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth(0.85F)
                        )
                        TextField(
                            shape = TextFieldDefaults.outlinedShape,
                            value = toTextState,
                            onValueChange = { toTextState = it },
                            label = { Text("To") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Black.copy(alpha = 0.0F),
                                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                                unfocusedIndicatorColor = Color.Black.copy(alpha = 0.0F),
                                focusedIndicatorColor = Color.Black.copy(alpha = 0.0F)
                            ),
                            singleLine = true
                        )
                    }
                    FloatingActionButton(
                        shape = FloatingActionButtonDefaults.smallShape,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(32.dp),
                        onClick = { },
                        content = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_swap_vert_24),
                                contentDescription = "Swap start and destination"
                            )
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun ConnectionSummary() {
    Surface(
        tonalElevation = 5.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("15 hr 58 min")
                Text("4 transfers")
                Text(
                    text = "16:00 (Thursday) - 7:58 (Friday)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(
                    modifier = Modifier.padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_directions_bus_24),
                        contentDescription = "Bus",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        "RB 68",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_train_24),
                        contentDescription = "Bus",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        "ICE 1234",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_local_taxi_24),
                        contentDescription = "Taxi",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1.0F))
            TextButton(onClick = { /*TODO*/ }) {
                Text("Buy ticket\n55.30 Lei")
            }
        }
    }
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
fun Search() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Search", "My Tickets", "Account")
    val icons = listOf(Icons.Filled.Search, Icons.Filled.List, Icons.Filled.AccountBox)
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .padding(
                        start = 14.dp,
                        bottom = it.calculateBottomPadding(),
                        top = 14.dp,
                        end = 14.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                SearchInput()
                Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    InputChip(
                        selected = false,
                        onClick = { /* TODO */ },
                        label = { Text("Depart at Thu, Jan 12, 14:21") },
                        trailingIcon = {
                            Icon(
                                Icons.Filled.ArrowDropDown,
                                contentDescription = "Click to change date and time settings",
                                Modifier.size(InputChipDefaults.AvatarSize)
                            )
                        }
                    )
                    InputChip(
                        selected = false,
                        onClick = { /* TODO */ },
                        label = { Text("All modes") },
                        trailingIcon = {
                            Icon(
                                Icons.Filled.ArrowDropDown,
                                contentDescription = "Click to change mode of transportation",
                                Modifier.size(InputChipDefaults.AvatarSize)
                            )
                        }
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    ConnectionSummary()
                    ConnectionSummary()
                    ConnectionSummary()
                    ConnectionSummary()
                    ConnectionSummary()
                }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Search()
    }
}