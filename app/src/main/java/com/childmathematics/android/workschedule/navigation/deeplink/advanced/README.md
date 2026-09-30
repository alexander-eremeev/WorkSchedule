# Deep Link Advanced Recipe

This recipe demonstrates how to apply the principles of navigation in the context of deep links by
managing a synthetic backStack and Task stacks.

# Recipe Structure
This recipe simulates a real-world scenario where "App A" deeplinks
into "App B".

"App A" is simulated by the module [com.example.nav3recipes.deeplink.advanced](/app/src/main/java/com/example/nav3recipes/deeplink/advanced), which
contains the `CreateAdvancedDeepLinkActivity` that allows you to create a deeplink intent and
trigger that in either the existing Task, or in a new Task.

"App B" is simulated by the module [advanceddeeplinkapp](/advanceddeeplinkapp/src/main/java/com/example/nav3recipes/deeplink/advanced), which contains
the MainActivity that you deeplink into. That module shows you how to build a synthetic backStack
and how to manage the Task stack properly in order to support both Back and Up buttons.

# Core implementation
The core helper functions for navigateUp and building synthetic backStack can be
found [here](/advanceddeeplinkapp/src/main/java/com/example/nav3recipes/deeplink/advanced/util/DeepLinkBackStackUtil.kt)

# Further Read
Check out the [deep link guide](/docs/deeplink-guide.md) for a 
comprehensive guide on Deep linking principles and how to apply them in Navigation 3.

# Расширенный рецепт для глубоких ссылок

Этот рецепт демонстрирует, как применять принципы навигации в контексте глубоких ссылок,
управляя синтетическим стеком возврата и стеками задач.

# Структура рецепта
Этот рецепт имитирует реальный сценарий, в котором «Приложение A» создает глубокую ссылку
на «Приложение B».

«Приложение A» имитируется модулем [com.example.nav3recipes.deeplink.advanced](/app/src/main/java/com/example/nav3recipes/deeplink/advanced), который
содержит `CreateAdvancedDeepLinkActivity`, позволяющий создать намерение глубокой ссылки и
запустить его либо в существующей задаче, либо в новой задаче.

Приложение "B" имитируется модулем 
[advanceddeeplinkapp](/advanceddeeplinkapp/src/main/java/com/example/nav3recipes/deeplink/advanced), 
который содержит
MainActivity, на которую вы создаете глубокую ссылку. Этот модуль показывает, как создать 
синтетический backStack,
и как правильно управлять стеком задач для поддержки кнопок "Назад" и "Вверх".

# Основная реализация
Основные вспомогательные функции для navigateUp и создания синтетического backStack можно найти
здесь]
(/advanceddeeplinkapp/src/main/java/com/example/nav3recipes/deeplink/advanced/util/DeepLinkBackStackUtil.kt)

# Дополнительная информация
Ознакомьтесь с [руководством по глубоким ссылкам](/docs/deeplink-guide.md) для
подробного руководства по принципам глубоких ссылок и их применению в Navigation 3.