# Deep Link URI Arguments Recipe

This recipe demonstrates how to parse a deep link URL from an Android Intent into a Navigation key.

## Recipe components

It consists of two activities
1. `UriWithArgumentsDeepLinkActivity` constructs and triggers the deeplink request
2. `MainActivity` parses the intent into the target navigation key.

## How it works

The `MainActivity` handles the request with these steps

1. Declare a `UriDeepLinkMatcher` for each url pattern that can be deep linked into. Each matcher accepts a uri pattern and the KSerializer of the NavKey that supports this deep link.
 
 2. Create a `DeepLinkRequest` with the incoming intent.
 
 3. Match all candidate `UriDeepLinkMatchers` with the request and compare the resulting `UriMatchResults` for the best match.
 
 4. Read the matching key from `UriMatchResult.key` or use default key if no match.

 This recipe focuses on handing an intent and does not include these considerations:
  - Create synthetic backStack
  - Multi-modular setup
  - DI
  - Managing TaskStack
  - Up button vs Back Button

## Demonstrated forms of deeplink

The `MainActivity` has several backStack keys to demonstrate different types of supported deep links:
1. `HomeKey` - deeplink with an exact url (no deeplink arguments)
2. `UsersKey` - deeplink with path arguments
3. `SearchKey` - deeplink with query arguments

See `MainActivity.deepLinkMatchers` for the actual url pattern of each.

# Рецепт: Обработка аргументов URI в диплинках

Этот рецепт демонстрирует, как преобразовать URL диплинка из Android Intent в ключ навигации.

## Компоненты рецепта

Пример состоит из двух Activity:
1. `UriWithArgumentsDeepLinkActivity` — формирует и инициирует запрос диплинка.
2. `MainActivity` — преобразует Intent в целевой ключ навигации.

## Принцип работы

`MainActivity` обрабатывает запрос следующим образом:

1. Объявляется `UriDeepLinkMatcher` для каждого шаблона URL, поддерживающего диплинки. Каждый матчер принимает шаблон URI и `KSerializer` для ключа навигации (`NavKey`), соответствующего данному диплинку.

2. Создается объект `DeepLinkRequest` на основе входящего Intent.

3. Выполняется сопоставление запроса со всеми доступными `UriDeepLinkMatchers`, а полученные результаты (`UriMatchResults`) сравниваются для выбора наиболее подходящего варианта.

4. Извлекается ключ из `UriMatchResult.key` (в случае совпадения) или используется ключ по умолчанию (если совпадений не найдено).

Данный рецепт фокусируется на обработке Intent и не затрагивает следующие аспекты:
- Создание синтетического стека переходов (back stack)
- Настройка многомодульной архитектуры
- Внедрение зависимостей (DI)
- Управление стеком задач (TaskStack)
- Различия между кнопкой «Вверх» (Up) и кнопкой «Назад» (Back)

## Демонстрируемые виды диплинков

В `MainActivity` определено несколько ключей навигации для демонстрации различных типов поддерживаемых диплинков:
1. `HomeKey` — диплинк с точным URL (без аргументов).
2. `UsersKey` — диплинк с аргументами в пути (path arguments).
3. `SearchKey` — диплинк с аргументами запроса (query arguments).

Конкретные шаблоны URL для каждого из них можно найти в `MainActivity.deepLinkMatchers`.