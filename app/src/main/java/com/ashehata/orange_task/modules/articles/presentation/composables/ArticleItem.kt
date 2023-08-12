package com.ashehata.orange_task.modules.articles.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ashehata.orange_task.modules.articles.presentation.model.ArticleUIModel

@Composable
fun ArticleItem(
    article: ArticleUIModel?,
    onArticleClicked: (ArticleUIModel) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .border(color = Color.Gray, shape = RoundedCornerShape(8.dp), width = 1.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                article?.let { onArticleClicked(it) }
            }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(article?.urlToImage)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Text(
            text = article?.title ?: "",
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSecondary
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }

}