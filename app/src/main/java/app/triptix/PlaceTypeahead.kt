import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import app.triptix.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceTypeahead(onCancel: () -> Unit) {
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
            if (!active) {
                focusManager.clearFocus()
            }
        },
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .clickable { onCancel() }
                    .size(InputChipDefaults.AvatarSize),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Go back to search"
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
    }
}