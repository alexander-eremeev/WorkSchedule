# Deep Link Recipes

This module contains the main recipes for deep linking with Navigation3.

## Recipe structure

The deep link module consists of two main packages:

### 1. usecases
Shows common cases for customizing deep link components
- `matcher` - a custom `DeepLinkMatcher` to parse deep links from `DeepLinkRequest` extras
- `serializer` - custom `DeepLinkSerializer` for URI deep link keys with non-primitive arguments
- `filter` - composite `DeepLinkMatcher.Filter` operators combining filters with infix functions (`and`, `or`)

### 2. handlerequests
Shows how to handle different types of deep link requests
- `staticuri` - handles deep links with a static Uri using `UriDeepLinkMatcher`.
- `uriwitharguments` - handles deep link with Uri arguments using `UriDeepLinkMatcher`
- `syntheticbackstack` - deep link between apps with a synthetic back stack using `DeepLinkMatcher.withBackStack` and correct "Up" navigation behavior

Примеры использования Deep Link

Этот модуль содержит основные примеры реализации глубоких ссылок (deep links) с помощью Navigation3.

## Структура модуля

Модуль глубоких ссылок состоит из двух основных пакетов:

### 1. usecases
Демонстрирует типичные сценарии настройки компонентов для работы с глубокими ссылками:
- `matcher` — кастомный `DeepLinkMatcher` для извлечения данных глубокой ссылки из параметров `DeepLinkRequest` (extras).
- `serializer` — кастомный `DeepLinkSerializer` для обработки ключей URI, содержащих аргументы не примитивных типов.
- `filter` — составные операторы `DeepLinkMatcher.Filter`, объединяющие фильтры с помощью инфиксных функций (`and`, `or`).

### 2. handlerequests
Демонстрирует способы обработки различных типов запросов глубоких ссылок:
- `staticuri` — обработка глубоких ссылок со статическим URI с использованием `UriDeepLinkMatcher`.
- `uriwitharguments` — обработка глубоких ссылок с аргументами в URI с использованием `UriDeepLinkMatcher`.
- `syntheticbackstack` — глубокая ссылка между приложениями с использованием синтетического стека переходов (`DeepLinkMatcher.withBackStack`) и корректной реализацией навигации «наверх» (Up navigation).