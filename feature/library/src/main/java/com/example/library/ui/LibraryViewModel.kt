package com.example.library.ui

import androidx.lifecycle.ViewModel
import com.example.library.model.Manga
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
open class CamListViewModel @Inject constructor() : ViewModel() {
    private var allUserManga = listOf<Manga>()

    private val _mangaList = MutableStateFlow<List<Manga>>(emptyList())
    val mangaList: StateFlow<List<Manga>> = _mangaList

    init {
        val manga = listOf(
            Manga("Naruto", "https://m.media-amazon.com/images/I/81YV7uoF8mL.jpg", "100", "50"),
            Manga("Bleach", "https://m.media-amazon.com/images/I/81SYVcwXeGL.jpg", "200", "50"),
            Manga(
                "Tokyo Ghoul",
                "https://anime-market.kiev.ua/product_images/product/cbc7ed48f871ec2c626386bb331c2507.jpg",
                "100", "50"
            ),
            Manga(
                "One piece",
                "https://static.wikia.nocookie.net/onepiece/images/4/4e/Volume_12.png/revision/latest?cb=20240219154213",
                "100", "50"
            ),
            Manga(
                "Sakamoto days",
                "https://static.wikia.nocookie.net/sakamoto-days/images/b/b6/Volume_13.png/revision/latest?cb=20230828122150",
                "100", "50"
            ),
        )
        allUserManga = manga
        updateMangaList(manga)
    }

    private fun updateMangaList(newList: List<Manga>) {
        _mangaList.value = newList
    }

    fun findManga(queryText: String) {
        _mangaList.value = allUserManga.filter { it.name.contains(queryText, true) }
    }
}