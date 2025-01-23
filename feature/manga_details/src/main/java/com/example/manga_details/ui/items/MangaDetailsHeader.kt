package com.example.manga_details.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.utils.utils.makeToast
import com.example.manga_details.R

@Composable
fun MangaDetailsHeader(
    onBackClick: () -> Unit,
    showTopBar: State<Boolean>,
    isAddedToLibrary: MutableState<Boolean>
) {
    val context = LocalContext.current

    Box(Modifier.fillMaxWidth()) {
        AsyncImage(
            model = "https://comicvine.gamespot.com/a/uploads/scale_large/6/67663/7897242-01.jpg",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Box(
            Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.9f),
                            Color.White.copy(alpha = 1f)
                        )
                    )
                )
        )

        if (!showTopBar.value) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val toastMessage =
                        if (isAddedToLibrary.value) R.string.manga_removed_from_favourites else R.string.manga_added_to_library

                    IconButton(onClick = {
                        isAddedToLibrary.value = !isAddedToLibrary.value
                        context.makeToast(context.getString(toastMessage))
                    }) {
                        Icon(
                            if (isAddedToLibrary.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(R.string.add_to_library),
                            tint = if (isAddedToLibrary.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Filled.MoreVert,
                            contentDescription = stringResource(R.string.more)
                        )
                    }
                }
            }
        }


        Row(
            Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(top = 80.dp, start = 16.dp),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = "https://comicvine.gamespot.com/a/uploads/scale_large/6/67663/7897242-01.jpg",
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.padding(start = 12.dp)) {
                Text(
                    "Sakamoto Days",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text("Yuuto Suzuki", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}