package com.webpa.mobile.ui.state

sealed class SearchUiState {
    data class Idle(val query: String) : SearchUiState()
    data class Loading(val query: String)  : SearchUiState()
//    data class Data(val query: String, val parsedData: ParsedObjectsUiModel) : SearchUiState()
    data class Error(val query: String, val message: String) : SearchUiState()
}