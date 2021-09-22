package openfoodfacts.rackluxury.scrachx.openfood.utils

import android.content.SearchRecentSuggestionsProvider
import openfoodfacts.rackluxury.scrachx.openfood.BuildConfig.APPLICATION_ID

class SearchSuggestionProvider : SearchRecentSuggestionsProvider() {
    companion object {
        const val AUTHORITY = "$APPLICATION_ID.utils.SearchSuggestionProvider"
        const val MODE = DATABASE_MODE_QUERIES
    }

    init {
        setupSuggestions(AUTHORITY, MODE)
    }
}