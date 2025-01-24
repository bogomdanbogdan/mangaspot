package com.example.manga_details.ui.chapters

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.core.utils.utils.formatRelativeTime
import com.example.data.model.chapter.Chapter
import com.example.manga_details.R

@Composable
fun ChapterListItem(context: Context, chapter: Chapter, onChapterClicked: (Chapter) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onChapterClicked.invoke(chapter) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                stringResource(R.string.chapter_placeholder, chapter.title),
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                context.formatRelativeTime(chapter.date),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 2.dp, bottom = 10.dp)
            )
        }

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.download),
            contentDescription = stringResource(R.string.download),
            modifier = Modifier.size(22.dp)
        )
    }
}
