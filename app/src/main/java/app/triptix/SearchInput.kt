import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import app.triptix.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchInput(click: () -> Unit) {
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
                Box(modifier = Modifier.width(340.dp)) {
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        var fromTextState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                            mutableStateOf(TextFieldValue("Dacia Service, Timișoara"))
                        }
                        var toTextState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                            mutableStateOf(TextFieldValue("Universitatea Româno-Americană"))
                        }
                        var focus = LocalFocusManager.current

                        var text by rememberSaveable { mutableStateOf("") }
                        var active by rememberSaveable { mutableStateOf(false) }
                        val focusManager = LocalFocusManager.current

                        val Transparent = Color.Black.copy(alpha = 0.0F)

                        fun closeSearchBar() {
                            focusManager.clearFocus()
                            active = false
                        }

//                        Box(Modifier.semantics { isContainer = true }.zIndex(1f).fillMaxWidth()) {
                            SearchBar(
//                                modifier = Modifier
                                    //.align(Alignment.TopCenter)
//                                    .size(350.dp, 500.dp),
                                query = text,
                                onQueryChange = { text = it },
                                onSearch = { closeSearchBar() },
                                active = active,
                                onActiveChange = {
                                    active = it
                                    if (!active) focusManager.clearFocus()
                                },
                                placeholder = { Text("Hinted search text") },
                                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                                trailingIcon = {
                                    Icon(
                                        Icons.Filled.Clear,
                                        contentDescription = "Go back to search",
                                        Modifier.size(InputChipDefaults.AvatarSize)
                                    )
                                },
                                colors = SearchBarDefaults.colors(
                                    inputFieldColors = TextFieldDefaults.textFieldColors(
                                        containerColor = Transparent,
                                        unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                                        unfocusedIndicatorColor = Transparent,
                                        focusedIndicatorColor = Transparent
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
//                            }
                        }
                        Divider(
                            color = MaterialTheme.colorScheme.outlineVariant,
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth(0.85F)
                        )
                        TextField(
                            modifier = Modifier.onFocusChanged {
                                if (it.hasFocus) {
                                    click()
                                    focus.clearFocus(true)
                                }
                            },
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