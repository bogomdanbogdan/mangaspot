package com.example.library.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.library.R
import com.example.library.ui.empty.EmptyLibraryScreen
import com.example.library.ui.manga.MangaGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    camListViewModel: CamListViewModel,
    onSearchPlaceholderClick: () -> (Unit?)
) {

    val searchQuery = remember { mutableStateOf("") }
    val isSearchVisible = remember { mutableStateOf(false) }
    val mangaList by camListViewModel.mangaList.collectAsState()
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.common_library)) },
                actions = {
                    IconButton(onClick = {
                        if (isSearchVisible.value) {
                            searchQuery.value = ""
                            camListViewModel.findManga("")
                        }
                        isSearchVisible.value = !isSearchVisible.value
                    }) {
                        Icon(
                            imageVector = if (isSearchVisible.value) {
                                Icons.Default.Close
                            } else {
                                Icons.Default.Search
                            },
                            contentDescription = stringResource(R.string.search_icon)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            if (isSearchVisible.value) {
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }

                OutlinedTextField(
                    value = searchQuery.value,
                    onValueChange = { text ->
                        searchQuery.value = text
                        camListViewModel.findManga(text)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .focusRequester(focusRequester),
                    placeholder = { Text(stringResource(R.string.search_dots)) }
                )
            }

            if (mangaList.isEmpty()) {
                EmptyLibraryScreen {
                    onSearchPlaceholderClick()
                }
            } else {
                MangaGrid(mangaList)
            }
        }
    }
}