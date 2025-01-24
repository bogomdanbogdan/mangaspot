package com.example.manga_details.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.data.model.chapter.manga_tag.MangaTag
import com.example.manga_details.R
import com.example.manga_details.ui.manga_tags.MangaTagItem

@Composable
fun MangaDetailsDescription(
    mangaDescriptionState: MutableState<Boolean>,
    interactionSource: MutableInteractionSource,
    mangaTags: List<MangaTag>
) {

    Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (!mangaDescriptionState.value) Modifier.height(100.dp) else Modifier.wrapContentHeight()
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        mangaDescriptionState.value = !mangaDescriptionState.value
                    }
                )
        ) {
            Text(
                "Taro Sakamoto is a living legend in the world of professional killers. Until recently, he had only loyal fans and enemies... and now he has a favorite girl who strictly told him to stop killing! Well, Sakamoto didn't think long and for the sake of his wedding with the love of his life, he discarded his criminal past, then put on weight and devoted himself to the family store and raising his beloved daughter.\n" +
                        "However, the world of hitmen has their own plans for Sakamoto, his former colleagues are not going to let him just walk away. Will Sakamoto, who has lost his former form, be able to protect himself and his loved ones?!\n"
            )
            if (!mangaDescriptionState.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.0f),
                                    Color.White.copy(alpha = 0.8f)
                                )
                            )
                        ),
                )
            }

            val arrowIcon = if (mangaDescriptionState.value) {
                Icons.Default.KeyboardArrowUp
            } else {
                Icons.Default.KeyboardArrowDown
            }

            Icon(
                imageVector = arrowIcon,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(24.dp),
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(mangaTags) { tag ->
                MangaTagItem(tag)
            }
        }

        Button(
            onClick = { /*TODO*/ },
            Modifier.padding(top = 16.dp).fillMaxWidth(),
        ) {
            Text(
                stringResource(R.string.read),
                color = MaterialTheme.colorScheme.background
            )
        }
    }
}