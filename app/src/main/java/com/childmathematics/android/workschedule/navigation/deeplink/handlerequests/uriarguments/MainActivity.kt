package com.childmathematics.android.workschedule.navigation.deeplink.handlerequests.uriarguments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.net.toUri
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.deeplink.DeepLinkRequest
import androidx.navigation3.runtime.deeplink.UriDeepLinkMatcher
import androidx.navigation3.runtime.deeplink.invoke
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.childmathematics.android.workschedule.navigation.common.deeplink.EntryScreen
import com.childmathematics.android.workschedule.navigation.common.deeplink.FriendsList
import com.childmathematics.android.workschedule.navigation.common.deeplink.LIST_USERS
import com.childmathematics.android.workschedule.navigation.common.deeplink.TextContent
import com.childmathematics.android.workschedule.navigation.deeplink.handlerequests.uriarguments.ui.URL_HOME_EXACT
import com.childmathematics.android.workschedule.navigation.deeplink.handlerequests.uriarguments.ui.URL_SEARCH
import com.childmathematics.android.workschedule.navigation.deeplink.handlerequests.uriarguments.ui.URL_USERS_WITH_FILTER
import com.childmathematics.android.workschedule.navigation.ui.setEdgeToEdgeConfig
import kotlinx.serialization.serializer

/**
 * See README.md for how this recipe works. Информацию о том, как работает этот рецепт, см. в файле README.md.
 */
class MainActivity : ComponentActivity() {
    /** STEP 1. Declare supported deep links
     * ШАГ 1. Объявите поддерживаемые глубокие ссылки.
     */
    internal val deepLinkMatchers: List<UriDeepLinkMatcher<NavKey>> = listOf(
        // "https://www.nav3recipes.com/home"
        UriDeepLinkMatcher(URL_HOME_EXACT.toUri(), serializer<HomeKey>()),
        // "https://www.nav3recipes.com/users/with/{filter}"
        UriDeepLinkMatcher(URL_USERS_WITH_FILTER.toUri(), serializer<UsersKey>()),
        // "https://www.nav3recipes.com/users/search?{firstName}&{age}&{location}"
        UriDeepLinkMatcher(URL_SEARCH.toUri(), serializer<SearchKey>()),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        setEdgeToEdgeConfig()
        super.onCreate(savedInstanceState)

        /** STEP 2. Create a [DeepLinkRequest] from the intent
         * ШАГ 2. Создайте [DeepLinkRequest] из объекта Intent.
         * */
        val request = DeepLinkRequest(intent)

        /** STEP 3. Match the request to the DeepLinkMatchers
         * ШАГ 3. Сопоставьте запрос с DeepLinkMatchers.
         * */
        // First get all the possible matching UriMatchResult
        // Сначала получите все возможные совпадающие объекты UriMatchResult.
        val matches = deepLinkMatchers.mapNotNull {
            // returns null if no match
            it.match(request)
        }
        // compare all matches to find best match
        val bestMatch = matches.maxOrNull()
        /** STEP 4. Get the key from the match or use default key if no match
         * ШАГ 4. Получите ключ из совпадения или используйте ключ по умолчанию, если совпадений нет.
         * */
        val key = bestMatch?.key ?: HomeKey

        /**
         * STEP 5. pass the initial key to backstack
         * ШАГ 5. Передайте начальный ключ в стек возврата.
         */
        setContent {
            val backStack: NavBackStack<NavKey> = rememberNavBackStack(key)
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<HomeKey> { key ->
                        EntryScreen(key.name) {
                            TextContent("<matches exact url>")
                        }
                    }
                    entry<UsersKey> { key ->
                        EntryScreen("${key.name} : ${key.filter}") {
                            TextContent("<matches path argument>")
                            val list = when {
                                key.filter.isEmpty() -> LIST_USERS
                                key.filter == UsersKey.FILTER_OPTION_ALL -> LIST_USERS
                                else -> LIST_USERS.take(5)
                            }
                            FriendsList(list)
                        }
                    }
                    entry<SearchKey> { search ->
                        EntryScreen(search.name) {
                            TextContent("<matches query parameters, if any>")
                            val matchingUsers = LIST_USERS.filter { user ->
                                (search.firstName == null || user.firstName == search.firstName) &&
                                        (search.location == null || user.location == search.location) &&
                                        (search.ageMin == null || user.age >= search.ageMin) &&
                                        (search.ageMax == null || user.age <= search.ageMax)
                            }
                            FriendsList(matchingUsers)
                        }
                    }
                }
            )
        }
    }
}