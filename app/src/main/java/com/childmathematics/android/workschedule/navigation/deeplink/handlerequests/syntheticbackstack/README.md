# Deep Link Synthetic BackStack Recipe

This recipe demonstrates how to apply the principles of navigation in the context of deep links by
managing a synthetic backStack and Task stacks.

# Recipe Structure
This recipe simulates a real-world scenario where "App A" deep links
into "App B".

"App A" is simulated by the module [syntheticbackstack](/app/src/main/java/com/example/nav3recipes/deeplink/handlerequests/syntheticbackstack), which
contains the `SyntheticBackStackDeepLinkActivity` that allows you to create a deeplink intent and
trigger that in either the existing Task, or in a new Task.

"App B" is simulated by the module [syntheticbackstackapp](/syntheticbackstackapp/src/main/java/com/example/nav3recipes/deeplink/syntheticbackstack), which contains
the `SyntheticBackStackAppActivity` that you deeplink into. That module shows you how to build a synthetic backStack
and how to manage the Task stack properly in order to support both Back and Up buttons.

# How to Use
Ensure both the main `app` and `syntheticbackstackapp` are installed on the emulator or connected device. Ensure that the installed `syntheticbackstackapp` supports
the `"www.nav3deeplink.com"` link.

On the recipe's landing page, choose the filters and click the button to deep link. It should bring you to the Activity of `syntheticbackstackapp`.

# How it Works
The recipe follows the deep link guideline summarized [here](/docs/deeplink-guide.md#summary).

To see behavior of `Existing Task`:
1. Open deep link using current task
2. On the device, swipe up to see all recent apps
3. Notice that the new Activity is opened within the Nav3Recipes app
4. Click back button to go back to the original Activity
5. Repeat step 1
6. Click the up button to go to parent screen
7. On the device, swipe up to see all recent apps
8. Notice that the new Activity is now opened within the Nav3SyntheticBackStack app

To see behavior of `New Task`:
1. Open deep link using new task
2. On the device, swipe up to see all recent apps
3. Notice that the new Activity is opened within the Nav3SyntheticBackStack app
4. Click Up or Back button to go to parent screen

# Core implementation
The core helper functions for navigateUp and building synthetic backStack can be
found [here](/syntheticbackstackapp/src/main/java/com/example/nav3recipes/deeplink/syntheticbackstack/util)

# Further Read
Check out the [deep link guide](/docs/deeplink-guide.md) for a 
comprehensive guide on Deep linking principles and how to apply them in Navigation 3.

# Рецепт: Создание синтетического стека возврата (Synthetic BackStack) для глубоких ссылок

Этот рецепт демонстрирует применение принципов навигации при работе с глубокими ссылками (deep links) посредством
управления синтетическим стеком возврата (synthetic back stack) и стеками задач (Task stacks).

# Структура рецепта
В этом рецепте моделируется реальный сценарий, в котором «Приложение A» переходит по глубокой ссылке
в «Приложение B».

«Приложение A» представлено модулем [syntheticbackstack](/app/src/main/java/com/example/nav3recipes/deeplink/handlerequests/syntheticbackstack),
содержащим `SyntheticBackStackDeepLinkActivity`. Это Activity позволяет создать Intent для глубокой ссылки и
запустить его либо в существующей задаче (Task), либо в новой.

«Приложение B» представлено модулем [syntheticbackstackapp](/syntheticbackstackapp/src/main/java/com/example/nav3recipes/deeplink/syntheticbackstack),
содержащим `SyntheticBackStackAppActivity`, на которую ведет глубокая ссылка. Этот модуль демонстрирует создание синтетического стека возврата
и правильное управление стеком задач для корректной работы кнопок «Назад» (Back) и «Вверх» (Up).

# Инструкция по использованию
Убедитесь, что оба приложения — основное (`app`) и целевое (`syntheticbackstackapp`) — установлены на эмуляторе или подключенном устройстве.
Убедитесь, что установленное приложение `syntheticbackstackapp` поддерживает
ссылку `"www.nav3deeplink.com"`.

На главной странице рецепта выберите фильтры и нажмите кнопку для перехода по глубокой ссылке. Это должно открыть Activity приложения `syntheticbackstackapp`.

# Принцип работы
Рецепт следует рекомендациям по работе с глубокими ссылками, изложенным [здесь](/docs/deeplink-guide.md#summary). Чтобы увидеть поведение при использовании `Existing Task` (существующей задачи):
1. Откройте дип-линк (deep link) в рамках текущей задачи.
2. Сделайте свайп вверх на устройстве, чтобы просмотреть список недавно запущенных приложений.
3. Обратите внимание, что новая Activity открыта внутри приложения `Nav3Recipes`.
4. Нажмите кнопку «Назад» (Back), чтобы вернуться к исходной Activity.
5. Повторите шаг 1.
6. Нажмите кнопку «Вверх» (Up), чтобы перейти к родительскому экрану.
7. Сделайте свайп вверх на устройстве, чтобы просмотреть список недавно запущенных приложений.
8. Обратите внимание, что теперь новая Activity открыта внутри приложения `Nav3SyntheticBackStack`.

Чтобы увидеть поведение при использовании `New Task` (новой задачи):
1. Откройте дип-линк в новой задаче.
2. Сделайте свайп вверх на устройстве, чтобы просмотреть список недавно запущенных приложений.
3. Обратите внимание, что новая Activity открыта внутри приложения `Nav3SyntheticBackStack`.
4. Нажмите кнопку «Вверх» (Up) или «Назад» (Back), чтобы перейти к родительскому экрану.

# Основная реализация
Основные вспомогательные функции для выполнения навигации вверх (`navigateUp`) и построения синтетического стека возврата (synthetic back stack) можно найти [здесь](/syntheticbackstackapp/src/main/java/com/example/nav3recipes/deeplink/syntheticbackstack/util).

# Дополнительная информация
Ознакомьтесь с [руководством по дип-линкам](/docs/deeplink-guide.md), чтобы получить исчерпывающую информацию о принципах работы с глубокими ссылками и их применении в Navigation 3.