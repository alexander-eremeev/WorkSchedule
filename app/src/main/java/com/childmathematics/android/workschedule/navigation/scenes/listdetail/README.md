# List-Detail Scene Recipe

This example shows how to create a list-detail layout using the Scenes API.

A `ListDetailSceneStrategy` will return a `ListDetailScene` if:

-   the window width is over 600dp
-   A `Detail` entry is the last item in the back stack
-   A `List` entry is in the back stack

The `ListDetailScene` provides a `CompositionLocal` named `LocalBackButtonVisibility` that can be used by the detail `NavEntry` to control whether it displays a back button. This is useful when the detail entry usually displays a back button but should not display it when being displayed in a `ListDetailScene`. See https://github.com/android/nav3-recipes/issues/151 for more details on this use case.

See `ListDetailScene.kt` for more implementation details.

Рецепт реализации сцены «Список — Детали»

В этом примере показано, как создать макет «Список — Детали» (list-detail) с помощью API сцен (Scenes API).

`ListDetailSceneStrategy` возвращает `ListDetailScene`, если:

-   ширина окна превышает 600dp;

-   элемент `Detail` является последним в стеке переходов (back stack);

-   в стеке переходов присутствует элемент `List`.

`ListDetailScene` предоставляет `CompositionLocal` с именем `LocalBackButtonVisibility`. Элемент `NavEntry`, 
отвечающий за отображение деталей, может использовать его для управления видимостью кнопки «Назад». 
Это полезно в ситуациях, когда кнопка «Назад» обычно отображается, но ее не следует показывать при нахождении 
элемента в `ListDetailScene`. Подробнее об этом сценарии использования см. по ссылке: https://github.com/android/nav3-recipes/issues/151.

Дополнительные сведения о реализации см. в файле `ListDetailScene.kt`.
