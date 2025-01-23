package com.example.manga_details.ui.manga_tags

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.model.chapter.manga_tag.MangaTag

@Composable
fun MangaTagItem(
    tag: MangaTag
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .height(35.dp)
            .background(MaterialTheme.colorScheme.background)
            .border(
                width = 0.8.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                shape = MaterialTheme.shapes.small
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = tag.tagName,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}