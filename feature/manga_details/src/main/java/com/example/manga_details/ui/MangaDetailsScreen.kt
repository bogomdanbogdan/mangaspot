package com.example.manga_details.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.data.model.chapter.Chapter
import com.example.manga_details.ui.chapters.ChapterHeader
import com.example.manga_details.ui.chapters.ChapterListItem
import com.example.manga_details.ui.items.MangaDetailsDescription
import com.example.manga_details.ui.items.MangaDetailsHeader
import com.example.manga_details.ui.items.MangaDetailsTopBar

@Composable
fun MangaDetailsScreen(
    mangaDetailsViewModel: MangaDetailsViewModel,
    onBackClick: () -> Unit,
    onChapterClicked: (Chapter) -> Unit
) {

    val chaptersList by mangaDetailsViewModel.chapterList.collectAsState()
    val tagsList by mangaDetailsViewModel.tagsList.collectAsState()

    val mangaDescriptionState = remember { mutableStateOf(false) }
    val chaptersSortState = remember { mutableStateOf(false) }
    val isAddedToLibrary = remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }
    val listState = rememberLazyListState()
    val showTopBar = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 200
        }
    }

    Box {
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                MangaDetailsHeader(onBackClick, showTopBar, isAddedToLibrary)
            }

            item {
                MangaDetailsDescription(mangaDescriptionState, interactionSource, tagsList)
            }

            item {
                ChapterHeader(chaptersList, chaptersSortState)
            }

            items(chaptersList) { chapter ->
                ChapterListItem(LocalContext.current, chapter) {
                    onChapterClicked.invoke(it)
                }
            }
        }

        if (showTopBar.value) {
            MangaDetailsTopBar(onBackClick, isAddedToLibrary)
        }
    }
}