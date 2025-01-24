package com.example.manga_details.ui

import androidx.lifecycle.ViewModel
import com.example.data.model.chapter.Chapter
import com.example.data.model.chapter.manga_tag.MangaTag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date
import javax.inject.Inject

open class MangaDetailsViewModel @Inject constructor() : ViewModel() {

    private val _chapterList = MutableStateFlow<List<Chapter>>(emptyList())
    val chapterList: StateFlow<List<Chapter>> = _chapterList

    private val _tagsList = MutableStateFlow<List<MangaTag>>(emptyList())
    val tagsList: StateFlow<List<MangaTag>> = _tagsList

    init {
        createChapters()
        createTags()
    }

    private fun createChapters() {
        val chapters = mutableListOf<Chapter>()
        val currentTime = System.currentTimeMillis()

        for (i in 0..40) {
            val date = Date(currentTime - i * 86400000)
            chapters.add(Chapter((i + 1).toString(), date))
        }

        _chapterList.value = chapters
    }

    private fun createTags() {
        val tags = mutableListOf<MangaTag>()

        for (i in 0..20) {
            if (i == 3) {
                tags.add(MangaTag("Big big big chip"))
            } else {
                tags.add(MangaTag("Tag $i"))
            }
        }

        _tagsList.value = tags
    }
}
