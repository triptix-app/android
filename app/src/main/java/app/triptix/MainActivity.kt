package de.triptix

import ConnectionSummary
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import app.triptix.R

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val Transparent = Color.Black.copy(alpha = 0.0F)

    fun closeSearchBar() {
        focusManager.clearFocus()
        active = false
    }

    SearchBar(
        modifier = Modifier.padding(top = 8.dp),
        query = text,
        onQueryChange = { text = it },
        onSearch = { closeSearchBar() },
        active = true,
        onActiveChange = {
            active = it
            if (!active) focusManager.clearFocus()
        },
        leadingIcon = {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Go back to search",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Clear,
                contentDescription = "Go back to search",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        placeholder = { Text("Darmstadt Hochschul") },
        colors = SearchBarDefaults.colors(
            inputFieldColors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Black.copy(alpha = 0.0F),
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Black.copy(alpha = 0.0F),
                focusedIndicatorColor = Color.Black.copy(alpha = 0.0F)
            )
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val list = listOf(
                "Hochschule",
                "Hochschule West",
                "Hochschulstadion",
                "Hochhaus der Hochschule",
                "Hochschulstraße"
            )
            val places = listOf(
                "Darmstadt",
                "Darmstadt",
                "Darmstadt",
                "Darmstadt",
                "Darmstadt"
            )
            val types = listOf(0, 0, 0, 1, 1)
            val icons = listOf(
                R.drawable.baseline_train_24,
                R.drawable.marker
            )

            repeat(list.size * 1) {
                val idx = it % list.size
                ListItem(
                    headlineText = { Text(list[idx]) },
                    supportingText = { Text(places[idx]) },
                    leadingContent = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = icons[types[idx]]),
                            contentDescription = if (types[idx] == 0) "Public Transport Stop" else "Street address",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    })
                Divider()
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
    val search = false
    Scaffold(
        content = {
            if (search) {
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
            } else {
                SearchBar()
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