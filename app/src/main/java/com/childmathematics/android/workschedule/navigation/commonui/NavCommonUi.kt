package com.childmathematics.android.workschedule.navigation.commonui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.childmathematics.android.workschedule.navigation.TopLevelBackStack
import com.childmathematics.android.workschedule.navigation.content.ContentBlue
import com.childmathematics.android.workschedule.navigation.content.ContentGreen
import com.childmathematics.android.workschedule.navigation.content.ContentPurple
import com.childmathematics.android.workschedule.navigation.content.ContentRed
import com.childmathematics.android.workschedule.navigation.ui.theme.WorkscheduleTheme

private sealed interface TopLevelRoute {
    val icon: ImageVector
}
private data object Home : TopLevelRoute { override val icon = Icons.Default.Home }
private data object ChatList : TopLevelRoute { override val icon = Icons.Default.Face }
private data object ChatDetail
private data object Camera : TopLevelRoute { override val icon = Icons.Default.PlayArrow }

private val TOP_LEVEL_ROUTES : List<TopLevelRoute> = listOf(Home, ChatList, Camera)
//class NavCommonUi {
    // CommonUiAct
    @Composable
    fun NavCommonUiAct() {
        val topLevelBackStack = remember { TopLevelBackStack<Any>(com.childmathematics.android.workschedule.navigation.commonui.Home) }
        WorkscheduleTheme {
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        TOP_LEVEL_ROUTES.forEach { topLevelRoute ->

                            val isSelected = topLevelRoute == topLevelBackStack.topLevelKey
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = {
                                    topLevelBackStack.addTopLevel(topLevelRoute)
                                },
                                icon = {
                                    Icon(
                                        imageVector = topLevelRoute.icon,
                                        contentDescription = null
                                    )
                                }
                            )
                        }
                    }
                }
            ) { innerPadding ->
                NavDisplay(
                    modifier = Modifier.padding(innerPadding),
                    backStack = topLevelBackStack.backStack,
                    onBack = { topLevelBackStack.removeLast() },
                    entryProvider = entryProvider {
                        entry<com.childmathematics.android.workschedule.navigation.commonui.Home> {
                            ContentRed("Home screen")
                        }
                        entry<com.childmathematics.android.workschedule.navigation.commonui.ChatList> {
                            ContentGreen("Chat list screen") {
                                Button(onClick = dropUnlessResumed {
                                    topLevelBackStack.add(com.childmathematics.android.workschedule.navigation.commonui.ChatDetail)
                                }) {
                                    Text("Go to conversation")
                                }
                            }
                        }
                        entry<ChatDetail> {
                            ContentBlue("Chat detail screen")
                        }
                        entry<com.childmathematics.android.workschedule.navigation.commonui.Camera> {
                            ContentPurple("Camera screen")
                        }
                    },
                )
            }
        }
    }
//}