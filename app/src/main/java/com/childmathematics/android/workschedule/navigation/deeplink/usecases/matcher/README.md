# Custom DeepLinkMatcher Recipe

This recipe demonstrates how to create a custom `DeepLinkMatcher` in Navigation 3 using custom request extras and Kotlinx Serialization.

## How it works

This recipe consists of two activities:
- `CustomDeepLinkMatcherActivity`: Accepts user input, serializes a `HomeKey` instance into JSON, attaches it to an `Intent` extra via a `RequestExtrasKey`, and launches `MainActivity`.
- `MainActivity`: Constructs a `DeepLinkRequest(intent)`, evaluates it with `JsonDeepLinkMatcher`, decodes the `HomeKey`, and sets it as the starting route in `NavDisplay`.

## Key Concepts

1. **Custom `RequestExtrasKey`**:
   `JsonDeepLinkMatcherKey` defines a custom extra key implementing `RequestExtrasKey<String>` to type-safely store and read serialized JSON payloads in `DeepLinkRequest.extras`.

2. **Custom `DeepLinkMatcher`**:
   `JsonDeepLinkMatcher<T>` extends `DeepLinkMatcher<T, MatchResult<T>>` and implements `matchRequest(request)` to extract `request.extras[JsonDeepLinkMatcherKey]` and decode it into a strongly typed `NavKey` using Kotlinx Serialization.

# Пример реализации пользовательского DeepLinkMatcher

В этом примере показано, как создать пользовательский `DeepLinkMatcher` в Navigation 3, используя кастомные дополнительные данные запроса (extras) и библиотеку Kotlinx Serialization.

## Принцип работы

Пример включает две Activity:
- `CustomDeepLinkMatcherActivity`: принимает ввод пользователя, сериализует экземпляр `HomeKey` в JSON, добавляет его в `Intent` (в поле extras) с помощью `RequestExtrasKey` и запускает `MainActivity`.
- `MainActivity`: формирует `DeepLinkRequest(intent)`, обрабатывает его с помощью `JsonDeepLinkMatcher`, десериализует `HomeKey` и устанавливает его в качестве начального маршрута в `NavDisplay`.

## Ключевые концепции

1. **Пользовательский `RequestExtrasKey`**:
   `JsonDeepLinkMatcherKey` определяет ключ для дополнительных данных, реализующий интерфейс `RequestExtrasKey<String>`. Это позволяет безопасно (с соблюдением типов) сохранять и считывать сериализованные JSON-данные в `DeepLinkRequest.extras`.

2. **Пользовательский `DeepLinkMatcher`**:
   `JsonDeepLinkMatcher<T>` расширяет `DeepLinkMatcher<T, MatchResult<T>>` и реализует метод `matchRequest(request)`, который извлекает данные по ключу `JsonDeepLinkMatcherKey` из `request.extras` и десериализует их в строго типизированный `NavKey` с помощью Kotlinx Serialization.