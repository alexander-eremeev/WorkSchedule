# Nav UI with Scene Decorators Recipe

This recipe demonstrates how to add UI elements such as top app bars and navigation bars or rails that you’d like to add at the scene, rather than nav entry level. To do this, it uses the scene decorator API.

## How it works

### The `NavigationScene` class

The `NavigationScene` class is the core of this recipe. It takes in a `Scene`, the current window size class, a `SharedTransitionScope`, and composables for a nav bar and nav rail. If the window width size class is medium or greater, it renders the nav rail on the start edge of the screen with the content on the end edge. Otherwise, it renders the nav bar on the bottom edge of the screen with the content on top.

#### Rendering shared UI elements only once

During transitions between scenes, both scenes are composed and rendered at the same time. For elements that are shared between scenes, such as a nav bar or rail, it may not be desirable for them to be composed in both scenes.

For example, the nav bar and rail composables in this recipe have some internal state that can't be hoisted (such as the state for the animations after an item is selected). As such, it's desirable to call the given composable only from one scene at any given time.

To accomplish the desired behavior, this recipe combines Compose's [movable content](https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary#movableContentOf(kotlin.Function0)) and [shared element](https://developer.android.com/develop/ui/compose/animation/shared-elements) APIs:

* By using `movableContentOf`, it is able to retain the state of the composable as it is moved between the different branches of the composition corresponding to each scene.
* By using the shared element APIs, it is able to keep the nav bar/rail in place while animating the content of the scenes that have been decorated. This is accomplished using the `sharedElement` modifier as well as a custom modifier, `cacheSize`, that maintains a placeholder of the correct size in the scene that doesn't call the movable content composable.

### The `NavigationSceneDecoratorStrategy` class

The `NavigationSceneDecoratorStrategy` class is responsible for wrapping the input scene in a `NavigationScene`. The `rememberNavigationSceneDecoratorStrategy` function simplifies the process of creating a `NavigationSceneDecoratorStrategy` by handling the creation of the `movableContentOf` composables. Generally, the `NavigationSceneDecoratorStrategy` should be one of, if not the final, items in the `sceneDecoratorStrategies` parameter so that it can contain all the other content of the app.

Рецепт использования декораторов сцены для элементов навигации

В этом рецепте показано, как добавлять элементы пользовательского интерфейса (например, верхние панели приложения,
навигационные панели или навигационные рейлы) на уровне сцены, а не на уровне отдельного экрана навигации. 
Для этого используется API декораторов сцены (scene decorator API).

## Принцип работы

### Класс `NavigationScene`

Класс `NavigationScene` — ключевой элемент этого рецепта. Он принимает объект `Scene`, класс размера текущего окна, 
`SharedTransitionScope`, а также composable-функции для навигационной панели и навигационного рейла. 
Если класс ширины окна соответствует категории «medium» (средний) или выше, навигационный рейл отображается 
у начального края экрана, а основной контент — у конечного. В противном случае навигационная панель размещается 
у нижнего края экрана, а контент — над ней.

#### Однократный рендеринг общих элементов интерфейса

При переходе между сценами обе сцены проходят этапы композиции и рендеринга одновременно. Однако для элементов, 
общих для обеих сцен (например, навигационной панели или рейла), дублирование композиции может быть нежелательным.

Например, используемые в этом рецепте composable-функции для навигационной панели и рейла имеют внутреннее состояние, 
которое невозможно вынести наружу (например, состояние анимации, запускаемой после выбора элемента). 
Поэтому желательно вызывать такую ​​функцию только в одной из сцен в конкретный момент времени.

Для реализации этого поведения в рецепте комбинируются API Compose для 
[перемещаемого контента](https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary#movableContentOf(kotlin.Function0)) 
и [общих элементов](https://developer.android.com/develop/ui/compose/animation/shared-elements):

*   Использование `movableContentOf` позволяет сохранить состояние composable-функции при ее перемещении 
между различными ветвями композиции, соответствующими разным сценам.
*   Использование API для общих элементов позволяет зафиксировать положение навигационной панели или рейла, 
одновременно анимируя контент декорируемых сцен. Это реализуется с помощью модификатора `sharedElement`, 
а также пользовательского модификатора `cacheSize`, который сохраняет в сцене заполнитель (плейсхолдер) нужного размера, 
не вызывая при этом composable-функцию перемещаемого контента (`movableContent`).

### Класс `NavigationSceneDecoratorStrategy`

Класс `NavigationSceneDecoratorStrategy` отвечает за обертку исходной сцены в `NavigationScene`. 
Функция `rememberNavigationSceneDecoratorStrategy` упрощает создание экземпляра `NavigationSceneDecoratorStrategy`, 
беря на себя создание composable-функций `movableContentOf`. Как правило, `NavigationSceneDecoratorStrategy` 
должен быть одним из последних (или даже самым последним) элементов в списке `sceneDecoratorStrategies`, 
чтобы охватывать весь остальной контент приложения.