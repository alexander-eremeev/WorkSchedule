# Two-Pane Scene Recipe

This example shows how to create a two pane layout using the Scenes API.

A `TwoPaneSceneStrategy` will return a `TwoPaneScene` if:

-   the window width is over 600dp
-   the last two nav entries on the back stack have indicated that they support being displayed in a `TwoPaneScene` in their metadata.

See `TwoPaneScene.kt` for more implementation details.

Рецепт реализации сцены с двумя панелями

В этом примере показано, как создать макет с двумя панелями с помощью API Scenes.

`TwoPaneSceneStrategy` возвращает `TwoPaneScene`, если:

-   ширина окна превышает 600dp;

-   две последние записи в стеке навигации (back stack) в своих метаданных указывают на поддержку отображения в `TwoPaneScene`.

Подробности реализации см. в файле `TwoPaneScene.kt`.