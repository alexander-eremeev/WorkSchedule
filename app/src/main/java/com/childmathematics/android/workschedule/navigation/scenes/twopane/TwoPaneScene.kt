package com.childmathematics.android.workschedule.navigation.scenes.twopane

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavMetadataKey
import androidx.navigation3.runtime.contains
import androidx.navigation3.runtime.metadata
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND

// --- TwoPaneScene ---
/**
 * A custom [Scene] that displays two [NavEntry]s side-by-side in a 50/50 split.
 * Пользовательская сцена ([Scene]), отображающая два элемента [NavEntry] рядом,
 * разделяя пространство между ними поровну (50/50).
 */
data class TwoPaneScene<T : Any>(
    override val key: Any,
    override val previousEntries: List<NavEntry<T>>,
    val firstEntry: NavEntry<T>,
    val secondEntry: NavEntry<T>
) : Scene<T> {
    override val entries: List<NavEntry<T>> = listOf(firstEntry, secondEntry)
    override val content: @Composable (() -> Unit) = {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(0.5f)) {
                firstEntry.Content()
            }
            Column(modifier = Modifier.weight(0.5f)) {
                secondEntry.Content()
            }
        }
    }

    companion object {
        /**
         * Helper function to add metadata to a [NavEntry] indicating it can be displayed
         * in a two-pane layout.
         * Вспомогательная функция для добавления к [NavEntry] метаданных, указывающих на
         * возможность отображения в двухпанельной компоновке.
         */
        fun twoPane() = metadata {
            put(TwoPaneKey, true)
        }
    }

    object TwoPaneKey : NavMetadataKey<Boolean>
}

@Composable
fun <T : Any> rememberTwoPaneSceneStrategy(): TwoPaneSceneStrategy<T> {
    val windowSizeClass = currentWindowAdaptiveInfoV2().windowSizeClass

    return remember(windowSizeClass) {
        TwoPaneSceneStrategy(windowSizeClass)
    }
}


// --- TwoPaneSceneStrategy ---
/**
 * A [SceneStrategy] that activates a [TwoPaneScene] if the window is wide enough
 * and the top two back stack entries declare support for two-pane display.
 * [SceneStrategy], активирующая [TwoPaneScene], если окно достаточно широкое,
 * * а две верхние записи в стеке переходов (back stack) поддерживают двухпанельный режим отображения.
 */
class TwoPaneSceneStrategy<T : Any>(val windowSizeClass: WindowSizeClass) : SceneStrategy<T> {

    override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {

        // Condition 1: Only return a Scene if the window is sufficiently wide to render two panes.
        // We use isWidthAtLeastBreakpoint with WIDTH_DP_MEDIUM_LOWER_BOUND (600dp).
        // Условие 1: Возвращать Scene только в том случае, если ширина окна достаточна для отображения двух панелей.
        //// Используем isWidthAtLeastBreakpoint с WIDTH_DP_MEDIUM_LOWER_BOUND (600dp).
        if (!windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND)) {
            return null
        }

        val lastTwoEntries = entries.takeLast(2)

        // Condition 2: Only return a Scene if there are two entries, and both have declared
        // they can be displayed in a two pane scene.
        // Условие 2: Возвращать сцену только при наличии двух элементов, если для обоих
        // заявлена ​​возможность отображения в двухпанельной сцене.
        return if (lastTwoEntries.size == 2
            && lastTwoEntries.all { it.metadata.contains(TwoPaneScene.TwoPaneKey) }
        ) {
            val firstEntry = lastTwoEntries.first()
            val secondEntry = lastTwoEntries.last()

            // The scene key must uniquely represent the state of the scene.
            // A Pair of the first and second entry keys ensures uniqueness.
            // Ключ сцены должен однозначно определять состояние сцены.
            //// Пара, состоящая из первого и второго ключей записи, гарантирует уникальность.
            val sceneKey = Pair(firstEntry.contentKey, secondEntry.contentKey)

            TwoPaneScene(
                key = sceneKey,
                // Where we go back to is a UX decision. In this case, we only remove the top
                // entry from the back stack, despite displaying two entries in this scene.
                // This is because in this app we only ever add one entry to the
                // back stack at a time. It would therefore be confusing to the user to add one
                // when navigating forward, but remove two when navigating back.
                // Выбор того, к какому экрану мы возвращаемся, — это решение, продиктованное
                // соображениями UX. В данном случае мы удаляем из стека переходов только верхнюю запись,
                // хотя на экране отображаются два элемента. Это связано с тем, что в этом приложении
                // в стек переходов всегда добавляется только одна запись за раз. Поэтому пользователя
                // сбило бы с толку, если бы при переходе вперед добавлялась одна запись,
                // а при возврате назад удалялись две.
                previousEntries = entries.dropLast(1),
                firstEntry = firstEntry,
                secondEntry = secondEntry
            )

        } else {
            null
        }
    }


}
