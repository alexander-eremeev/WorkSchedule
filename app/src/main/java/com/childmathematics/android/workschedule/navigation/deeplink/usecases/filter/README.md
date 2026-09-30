# Composite DeepLinkMatcher.Filter Recipe

This recipe demonstrates how to combine multiple `DeepLinkMatcher.Filter` instances using infix functions (`and`, `or`) to create composite filtering logic in Navigation 3.

## How it works

`DeepLinkMatcher` natively evaluates a list of filters using implicit logical AND (`filters.all { it.filterRequest(request) }`). By defining infix operator functions (`and`, `or`), developers can construct flexible boolean expressions combining intent actions, MIME types, or custom criteria.

This recipe consists of two activities:
- `CompositeFilterDeepLinkActivity`: An interactive playground allowing you to configure the intent's action and MIME type, preview whether the composite filter will match, and launch the deep link request.
- `MainActivity`: Constructs a `DeepLinkRequest(intent)`, matches it using a `UriDeepLinkMatcher` configured with a composite filter (`actionFilter(ACTION_VIEW) and (mimeTypeFilter("image/png") or mimeTypeFilter("image/jpeg"))`), and navigates to either `ViewerKey` or `FallbackKey`.

## Key Concepts

1. **Infix `and` Operator**:
   Combines two filters using logical AND with short-circuiting:
   ```kotlin
   infix fun Filter.and(other: Filter): Filter = Filter { request ->
       filterRequest(request) && other.filterRequest(request)
   }
   ```

2. **Infix `or` Operator**:
   Combines two filters using logical OR with short-circuiting:
   ```kotlin
   infix fun Filter.or(other: Filter): Filter = Filter { request ->
       filterRequest(request) || other.filterRequest(request)
   }
   ```

3. **Operator `!` (NOT)**:
   Inverts the result of a filter using logical NOT:
   ```kotlin
   operator fun Filter.not(): Filter = Filter { request ->
       !filterRequest(request)
   }
   ```

4. **Composite Filter Expressions**:
   Operators allow expressive and readable composition:
   ```kotlin
   val imageMimeTypeFilter = DeepLinkMatcher.mimeTypeFilter("image/png") or
       DeepLinkMatcher.mimeTypeFilter("image/jpeg")
   val compositeFilter = DeepLinkMatcher.actionFilter(Intent.ACTION_VIEW) and
       imageMimeTypeFilter and !DeepLinkMatcher.mimeTypeFilter("application/pdf")

   val matcher = UriDeepLinkMatcher(
       uriPattern = VIEWER_URI_PATTERN.toUri(),
       serializer = serializer<ViewerKey>(),
       filters = listOf(compositeFilter)
   )
   ```

# Рецепт использования составного `DeepLinkMatcher.Filter`

Этот рецепт демонстрирует, как объединять несколько экземпляров `DeepLinkMatcher.Filter` с помощью инфиксных функций (`and`, `or`) для создания составной логики фильтрации в Navigation 3.

## Принцип работы

`DeepLinkMatcher` по умолчанию проверяет список фильтров, используя неявную логическую операцию И (`filters.all { it.filterRequest(request) }`). Определяя инфиксные функции-операторы (`and`, `or`), разработчики могут создавать гибкие булевы выражения, объединяющие действия Intent, MIME-типы или пользовательские критерии.

Данный пример включает две Activity:
- `CompositeFilterDeepLinkActivity`: Интерактивный экран, позволяющий настроить действие (action) и MIME-тип Intent, проверить, сработает ли составной фильтр, и запустить запрос диплинка (deep link).
- `MainActivity`: Создает `DeepLinkRequest(intent)`, выполняет сопоставление с помощью `UriDeepLinkMatcher`, настроенного с составным фильтром (`actionFilter(ACTION_VIEW) and (mimeTypeFilter("image/png") or mimeTypeFilter("image/jpeg"))`), и выполняет навигацию к `ViewerKey` или `FallbackKey`.

## Ключевые концепции

1. **Инфиксный оператор `and`**:
   Объединяет два фильтра с помощью логического И (с механизмом короткого замыкания):
```kotlin
infix fun Filter.and(other: Filter): Filter = Filter { request ->
filterRequest(request) && other.filterRequest(request)
}
```

2. **Инфиксный оператор `or`**:
   Объединяет два фильтра с помощью логического ИЛИ (с механизмом короткого замыкания):
```kotlin

3.
   Инвертирует результат фильтрации с помощью логического НЕ (NOT)

```

4. **Составные выражения фильтров**:
   Операторы позволяют создавать выразительные и легко читаемые комбинации:
```kotlin
val imageMimeTypeFilter = DeepLinkMatcher.mimeTypeFilter("image/png") or
DeepLinkMatcher.mimeTypeFilter("image/jpeg")
val compositeFilter = DeepLinkMatcher.actionFilter(Intent.ACTION_VIEW) and
imageMimeTypeFilter and !DeepLinkMatcher.mimeTypeFilter("application/pdf")

val matcher = UriDeepLinkMatcher(
uriPattern = VIEWER_URI_PATTERN.toUri(),
serializer = serializer<ViewerKey>(),
filters = listOf(compositeFilter)
)
```