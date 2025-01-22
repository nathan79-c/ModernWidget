package com.example.modernwidget.glance

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import kotlinx.coroutines.delay

class MyAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                MyContent()
            }
        }
    }
}


@Composable
private fun MyContent() {
    val verses = listOf(
        "The Lord is my shepherd; I shall not want. - Psalm 23:1",
        "I can do all things through Christ who strengthens me. - Philippians 4:13",
        "For I know the plans I have for you, declares the Lord. - Jeremiah 29:11",
        "The joy of the Lord is your strength. - Nehemiah 8:10"
    )

    var currentVerseIndex by remember { mutableIntStateOf(0) }

    // Change the verse every few seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000) // Wait for 5 seconds
            currentVerseIndex = (currentVerseIndex + 1) % verses.size
        }
    }

    Column(
        modifier = GlanceModifier.fillMaxSize()
            .background(GlanceTheme.colors.background)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val currentVerse = verses[currentVerseIndex]
        val parts = currentVerse.split(" - ")
        val verseText = parts[0]
        val verseReference = if (parts.size > 1) parts[1] else ""

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = verseText,
                modifier = GlanceModifier.padding(bottom = 8.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = GlanceTheme.colors.onBackground
                )
            )
            Text(
                text = verseReference,
                modifier = GlanceModifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 14.sp,
                    textAlign = TextAlign.End,
                    color = GlanceTheme.colors.onBackground
                )
            )
        }
    }
}



