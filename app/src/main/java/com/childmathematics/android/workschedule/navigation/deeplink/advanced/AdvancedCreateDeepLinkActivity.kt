package com.childmathematics.android.workschedule.navigation.deeplink.advanced

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import com.childmathematics.android.workschedule.navigation.deeplink.common.EntryScreen
import com.childmathematics.android.workschedule.navigation.deeplink.common.LIST_FIRST_NAMES
import com.childmathematics.android.workschedule.navigation.deeplink.common.LIST_LOCATIONS
import com.childmathematics.android.workschedule.navigation.deeplink.common.MenuDropDown
import com.childmathematics.android.workschedule.navigation.deeplink.common.PaddedButton
import com.childmathematics.android.workschedule.navigation.deeplink.common.TextContent

internal const val ADVANCED_PATH_BASE = "https://www.nav3deeplink.com"

/**
 * The recipe entry point that allows users to create a deep link and make a request with it.
 *
 * **HOW THIS RECIPE WORKS** This recipe simulates a real-world scenario where "App A" deeplinks
 * into "App B".
 *
 * "App A" is simulated by this current module [com.childmathematics.android.workschedule.navigation.deeplink.advanced], which
 * contains the [AdvancedCreateDeepLinkActivity] that allows you to create a deeplink intent and
 * trigger that in either the existing Task, or in a new Task.
 *
 * "App B" is simulated by the module [com.childmathematics.android.workschedule.navigation.deeplink.advanced], which contains
 * the MainActivity that you deeplink into. That module shows you how to build a synthetic backStack
 * and how to manage the Task stack properly in order to support both Back and Up buttons.
 *
 * See the [README](README.md) file of current module for more info on advanced deep linking.
 * * Точка входа в рецепт, позволяющая пользователям создавать глубокую ссылку и отправлять с её помощью запрос.
 *
 * *
 * * **КАК РАБОТАЕТ ЭТОТ РЕЦЕПТ** Этот рецепт имитирует реальный сценарий, в котором «Приложение A» создает
 * глубокую ссылку
 * * на «Приложение B».
 *
 * *
 *
 * * «Приложение A» имитируется текущим модулем [com.childmathematics.android.workschedule.navigation.deeplink.advanced], который
 * * содержит [AdvancedCreateDeepLinkActivity], позволяющий создать намерение глубокой ссылки и
 * * запустить его либо в существующей задаче, либо в новой задаче.
 *
 * *
 * * «Приложение B» имитируется модулем [com.childmathematics.android.workschedule.navigation.deeplink.advanced], который содержит
 * * MainActivity, на которую вы создаете глубокую ссылку. Этот модуль показывает, как создать синтетический
 * стек возврата,
 *
 * * и как правильно управлять стеком задач для поддержки кнопок «Назад» и «Вверх».
 *
 * * *
 *
 * * Дополнительную информацию о расширенных возможностях глубокой перелинковки см. в файле [README](README.md)
 * текущего модуля.
 */
class AdvancedCreateDeepLinkActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EntryScreen("Sandbox - Build Your Deeplink Intent") {
                val initFirstName = MENU_OPTIONS_FIRST_NAME.values.first().first()
                val initLocation = MENU_OPTIONS_LOCATION.values.last().first()
                val initTaskStack = MENU_OPTIONS_TASK_STACK.values.first().first()
                var firstName by remember { mutableStateOf(initFirstName) }
                var location by remember { mutableStateOf(initLocation) }
                var taskStack by remember { mutableStateOf(initTaskStack) }

                // select first name
                MenuDropDown(
                    menuOptions = MENU_OPTIONS_FIRST_NAME,
                ) { _, selected ->
                    firstName = selected
                }

                // select first name
                MenuDropDown(
                    menuOptions = MENU_OPTIONS_LOCATION,
                ) { _, selected ->
                    location = selected
                }

                // select current task stack or build new task stack
                // Выберите текущий стек задач или создайте новый стек задач
                MenuDropDown(
                    menuOptions = MENU_OPTIONS_TASK_STACK,
                ) { _, selected ->
                    taskStack = selected
                }

                // build final deeplink URL and Intent
                // Создание окончательного URL-адреса диплинка и намерения
                val finalUrl = "${ADVANCED_PATH_BASE}/user/$firstName/$location"

                // display Intent info
                val flagString = if (taskStack == TAG_NEW_TASK) {
                    "Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK"
                } else "<none>"
                val intentString = """
                    | Final Intent:
                    | data = "$finalUrl"
                    | action = Intent.ACTION_VIEW
                    | flags = $flagString
                """.trimMargin()

                TextContent(intentString)

                // deeplink to target
                PaddedButton("Deeplink Away!") {
                    val intent = Intent().apply {
                        data = finalUrl.toUri()
                        action = Intent.ACTION_VIEW
                        if (taskStack == TAG_NEW_TASK) {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                    }

                    startActivity(intent)
                }
            }
        }
    }
}

private const val TAG_FIRST_NAME = "firstName"
private const val TAG_LOCATION = "location"
private const val TAG_TASK_STACK = "Task stack"
private const val TAG_CURRENT_TASK = "Use Current Task Stack"
private const val TAG_NEW_TASK = "Start New Task Stack"

private val MENU_OPTIONS_FIRST_NAME = mapOf(TAG_FIRST_NAME to LIST_FIRST_NAMES)

private val MENU_OPTIONS_LOCATION = mapOf(TAG_LOCATION to LIST_LOCATIONS)

private val MENU_OPTIONS_TASK_STACK = mapOf(TAG_TASK_STACK to listOf(TAG_CURRENT_TASK, TAG_NEW_TASK),)
