# Deep Link Basic Recipe

This recipe demonstrates how to parse a deep link URL from an Android Intent into a Navigation key.

## How it works

It consists of two activities - `CreateDeepLinkActivity` to construct and trigger the deeplink request, and the `MainActivity` to show how an app can handle that request.

## Demonstrated forms of deeplink

The `MainActivity` has several backStack keys to demonstrate different types of supported deeplinks:
1. `HomeKey` - deeplink with an exact url (no deeplink arguments)
2. `UsersKey` - deeplink with path arguments
3. `SearchKey` - deeplink with query arguments

See `MainActivity.deepLinkPatterns` for the actual url pattern of each.

## Recipe structure

This recipe consists of three main packages:
1. `basic.deeplink` - Contains the two activities
2. `basic.deeplink.ui` - Contains the activity UI code, i.e. global string variables, deeplink URLs etc
3. `basic.deeplink.util` - Contains the classes and helper methods to parse and match the deeplinks

# Базовый рецепт создания глубоких ссылок

Этот рецепт демонстрирует, как преобразовать URL-адрес глубокой ссылки из Android Intent в навигационную 
клавишу.

## Как это работает

Он состоит из двух активностей: `CreateDeepLinkActivity` для создания и запуска запроса на создание
глубокой ссылки и `MainActivity` для демонстрации того, как приложение может обрабатывать этот запрос.

## Демонстрируемые формы глубоких ссылок

В `MainActivity` есть несколько ключей backStack для демонстрации различных типов поддерживаемых 
глубоких ссылок:
1. `HomeKey` — глубокая ссылка с точным URL-адресом (без аргументов глубокой ссылки)
2. `UsersKey` — глубокая ссылка с аргументами пути
3. `SearchKey` — глубокая ссылка с аргументами запроса

См. `MainActivity.deepLinkPatterns` для фактического шаблона URL-адреса для каждого типа.

## Структура рецепта

Этот рецепт состоит из трех основных пакетов:
1. `basic.deeplink` — содержит два интерактивных элемента.
2. `basic.deeplink.ui` — содержит код пользовательского интерфейса интерактивного элемента, 
3. т. е. глобальные строковые переменные, URL-адреса диплинков и т. д.
3. `basic.deeplink.util` — содержит классы и вспомогательные методы для анализа и сопоставления 
4. диплинков.