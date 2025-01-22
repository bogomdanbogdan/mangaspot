package com.example.bottom_bar.ui

import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.bottom_bar.tabs.Tab
import com.example.bottom_bar.ui.res.LocalDimen

@Composable
fun BottomTab(
    tab: Tab,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val animatedIcon = remember { AnimatedVectorDrawableCompat.create(context, tab.iconActive) }
    val selectedColor = MaterialTheme.colorScheme.primary.toArgb()
    val unselectedColor = MaterialTheme.colorScheme.onPrimary.toArgb()

    LaunchedEffect(selected) {
        animatedIcon?.apply {
            if (selected) {
                start()
            }
        }
    }

    Surface(
        modifier = modifier,
        onClick = onClick,
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier.size(LocalDimen.current.iconSize)
                ) {
                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { context ->
                            ImageView(context).apply {
                                setImageDrawable(animatedIcon)
                                setColorFilter(
                                    if (selected) selectedColor else unselectedColor,
                                    PorterDuff.Mode.SRC_IN
                                )
                            }
                        },
                        update = { imageView ->
                            imageView.setImageDrawable(animatedIcon)
                            imageView.setColorFilter(
                                if (selected) selectedColor else unselectedColor,
                                PorterDuff.Mode.SRC_IN
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(LocalDimen.current.iconTextPadding))

                Text(
                    text = stringResource(id = tab.title),
                    color = if (selected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    )
}