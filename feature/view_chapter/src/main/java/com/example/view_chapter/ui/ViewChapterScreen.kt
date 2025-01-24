package com.example.view_chapter.ui

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.view_chapter.R
import com.example.view_chapter.utils.LocalColors
import kotlinx.coroutines.launch
import java.io.OutputStream

@Composable
fun ViewChapterScreen(
    viewChapterViewModel: ViewChapterViewModel,
    chapterNumber: String,
    mangaName: String,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val images = remember { getRawImages() }
    val pagerState = rememberPagerState { images.size }
    val coroutineScope = rememberCoroutineScope()

    var controlsVisible by remember { mutableStateOf(false) }
    var showSaveDialog by remember { mutableStateOf(false) }
    var selectedImage by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { controlsVisible = !controlsVisible },
                        onLongPress = {
                            selectedImage = images[pagerState.currentPage]
                            showSaveDialog = true
                        }
                    )
                }
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = stringResource(R.string.image_placeholder, page + 1),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }


        if (showSaveDialog) {
            SaveImageDialog(
                onDismiss = { showSaveDialog = false },
                onSave = {
                    saveImageToGallery(context, selectedImage, mangaName)
                    showSaveDialog = false
                }
            )
        }

        AnimatedVisibility(
            visible = controlsVisible,
            enter = fadeIn() + slideInVertically(initialOffsetY = { -it }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { -it })
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.8f))
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .background(
                                LocalColors.current.elementBgc,
                                shape = RoundedCornerShape(50.dp)
                            )
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = stringResource(R.string.navigate_back),
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(R.string.chapter_placeholder, chapterNumber),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .background(
                                LocalColors.current.elementBgc,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .background(
                                LocalColors.current.elementBgc,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .clip(RoundedCornerShape(50.dp))
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_save),
                            contentDescription = stringResource(R.string.save_chapter),
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(4.dp)
                        )
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = controlsVisible,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it }),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.8f))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            val prevPage = (pagerState.currentPage - 1).coerceAtLeast(0)
                            pagerState.animateScrollToPage(prevPage)
                        }
                    },
                    enabled = pagerState.currentPage > 0,
                    modifier = Modifier.padding(start = 4.dp).background(
                        LocalColors.current.elementBgc,
                        shape = RoundedCornerShape(50.dp)
                    )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_prev_page),
                        contentDescription = stringResource(R.string.previous_image),
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .background(
                            LocalColors.current.elementBgc,
                            shape = RoundedCornerShape(50.dp)
                        ).padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "${pagerState.currentPage + 1}",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(end = 12.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Slider(
                        value = pagerState.currentPage.toFloat(),
                        onValueChange = { newValue ->
                            coroutineScope.launch {
                                pagerState.scrollToPage(newValue.toInt())
                            }
                        },
                        valueRange = 0f..(images.size - 1).toFloat(),
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.primary,
                            activeTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                            inactiveTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        ),
                        modifier = Modifier.width(180.dp)
                    )

                    Text(
                        text = "${images.size}",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 12.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            val nextPage =
                                (pagerState.currentPage + 1).coerceAtMost(images.size - 1)
                            pagerState.animateScrollToPage(nextPage)
                        }
                    },
                    enabled = pagerState.currentPage < images.size - 1,
                    modifier = Modifier.background(
                        LocalColors.current.elementBgc,
                        shape = RoundedCornerShape(50.dp)
                    )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_next_page),
                        contentDescription = stringResource(R.string.next_page),
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

fun getRawImages(): List<Int> {
    val fields = R.raw::class.java.fields
    return fields.mapNotNull { field ->
        try {
            field.getInt(null)
        } catch (e: Exception) {
            null
        }
    }
}

@Composable
fun SaveImageDialog(onDismiss: () -> Unit, onSave: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        title = {
            Text(
                text = stringResource(R.string.save_image),
                color = Color.Black
            )
        },
        text = {
            Text(
                text = stringResource(R.string.do_you_want_to_save_image_to_gallery),
                color = Color.Black
            )
        },
        confirmButton = {
            TextButton(onClick = onSave) {
                Text(stringResource(R.string.save), color = Color.Black)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel), color = Color.Black)
            }
        },
        modifier = Modifier.padding(8.dp)
    )
}


fun saveImageToGallery(context: Context, imageResId: Int, mangaName: String) {
    val bitmap = BitmapFactory.decodeResource(context.resources, imageResId)

    val contentResolver = context.contentResolver
    val imageCollection =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

    val imageName = "$mangaName + _ {System.currentTimeMillis()}.jpg"

    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, imageName)
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/SpotManga")
    }

    val imageUri = contentResolver.insert(imageCollection, contentValues)

    imageUri?.let { uri ->
        val outputStream: OutputStream? = contentResolver.openOutputStream(uri)
        outputStream?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
        Toast.makeText(context, context.getString(R.string.image_saved), Toast.LENGTH_SHORT).show()
    } ?: run {
        Toast.makeText(
            context,
            context.getString(R.string.unable_to_save_image),
            Toast.LENGTH_SHORT
        ).show()
    }
}