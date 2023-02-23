package de.triptix

import ConnectionSummary
import PlaceTypeahead
import SearchInput
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.zIndex
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

enum class UiState {
    SEARCH,
    MY_TICKETS,
    TYPEAHEAD
}


@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
fun Search() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Search", "My Tickets", "Account")
    val icons = listOf(Icons.Filled.Search, Icons.Filled.List, Icons.Filled.AccountBox)
    var state by remember { mutableStateOf(UiState.SEARCH) }
    Scaffold(
        content = {
            Log.i("State", "State ${state}")
            when (state) {
                UiState.SEARCH -> Column(
                    modifier = Modifier
                        .padding(
                            start = 14.dp,
                            bottom = it.calculateBottomPadding(),
                            top = 14.dp,
                            end = 14.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    SearchInput {
                        state = UiState.TYPEAHEAD
                        Log.i("TextField", "Clicked")
                    }
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


                UiState.TYPEAHEAD -> PlaceTypeahead(onCancel = { state = UiState.SEARCH })

                UiState.MY_TICKETS -> { /* TODO */
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Search()
    }
}