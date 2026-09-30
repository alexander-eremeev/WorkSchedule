package com.childmathematics.android.workschedule.navigation.deeplink.basic

import androidx.navigation3.runtime.NavKey
import com.childmathematics.android.workschedule.navigation.deeplink.basic.ui.STRING_LITERAL_FILTER
import com.childmathematics.android.workschedule.navigation.deeplink.basic.ui.STRING_LITERAL_HOME
import com.childmathematics.android.workschedule.navigation.deeplink.basic.ui.STRING_LITERAL_SEARCH
import com.childmathematics.android.workschedule.navigation.deeplink.basic.ui.STRING_LITERAL_USERS
import kotlinx.serialization.Serializable

internal interface NavRecipeKey: NavKey {
    val name: String
}

@Serializable
internal object HomeKey: NavRecipeKey {
    override val name: String = STRING_LITERAL_HOME
}

@Serializable
internal data class UsersKey(
    val filter: String,
): NavRecipeKey {
    override val name: String = STRING_LITERAL_USERS
    companion object {
        const val FILTER_KEY = STRING_LITERAL_FILTER
        const val FILTER_OPTION_RECENTLY_ADDED = "recentlyAdded"
        const val FILTER_OPTION_ALL = "all"
    }
}

@Serializable
internal data class SearchKey(
    val firstName: String? = null,
    val ageMin: Int? = null,
    val ageMax: Int? = null,
    val location: String? = null,
): NavRecipeKey {
    override val name: String = STRING_LITERAL_SEARCH
}