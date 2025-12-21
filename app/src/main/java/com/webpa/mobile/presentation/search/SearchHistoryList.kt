package com.webpa.mobile.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.webpa.mobile.domain.model.SearchHistoryItem

@Composable
fun SearchHistoryList(
    history: List<SearchHistoryItem>,
    onItemClick: (String) -> Unit
) {
    Column {
        Text(
            text = "История поиска",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        history.forEach { item ->
            SearchHistoryItemRow(
                query = item.query,
                onClick = { onItemClick(item.query) }
            )
        }
    }
}

@Composable
private fun SearchHistoryItemRow(
    query: String,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        tonalElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = query,
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

