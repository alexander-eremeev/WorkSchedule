# Custom DeepLinkSerializer Recipe

This recipe demonstrates how to use a custom `DeepLinkSerializer` in Navigation 3 using `UriDeepLinkMatcher` and Kotlinx Serialization.

## How it works

This recipe consists of two activities:
- `DeepLinkSerializerActivity`: Takes user input for product details (name, color, and quantity), serializes the custom `Product` object into a URI query parameter string (`?product={product}&quantity={quantity}`), constructs a `DeepLinkRequest` URI, and launches `MainActivity`.
- `MainActivity`: Constructs a `DeepLinkRequest(intent)`, matches it using `UriDeepLinkMatcher` configured with `ProductDetailsKey` (which uses `ProductSerializer` for `product` and native serialization for `quantity`), and displays the deserialized product details in `NavDisplay`.

## Key Concepts

1. **`DeepLinkSerializer<T>`**:
   Extends `DeepLinkSerializer<T>` to provide custom string serialization and deserialization logic for custom objects or types (such as `Color` or packed string formats) that need to be parsed from or encoded into URI path/query parameters.

2. **Annotating NavKey properties with `@Serializable(with = ...)`**:
   The custom `NavKey` uses `@Serializable(with = ProductSerializer::class)` on properties whose types require custom serialization (e.g. `product: Product`).

3. **Mixing Standard and Custom Serialized Parameters**:
   Parameters with standard types (like `quantity: Int`) are serialized natively out-of-the-box by Kotlinx Serialization, while custom parameters (like `product: Product`) use `ProductSerializer`.

4. **`UriDeepLinkMatcher` integration**:
   `UriDeepLinkMatcher(uriPattern, serializer<ProductDetailsKey>())` automatically applies custom and standard serializers when matching and decoding deep link URIs.

Пример использования пользовательского `DeepLinkSerializer`

В этом примере показано, как использовать пользовательский `DeepLinkSerializer` в Navigation 3 с применением `UriDeepLinkMatcher` и Kotlinx Serialization.

## Принцип работы

Пример включает две Activity:
- `DeepLinkSerializerActivity`: принимает от пользователя данные о товаре (название, цвет и количество), сериализует пользовательский объект `Product` в строку параметров запроса URI (`?product={product}&quantity={quantity}`), формирует URI для `DeepLinkRequest` и запускает `MainActivity`.
- `MainActivity`: создает `DeepLinkRequest(intent)`, выполняет сопоставление с помощью `UriDeepLinkMatcher`, настроенного с использованием `ProductDetailsKey` (где для `product` применяется `ProductSerializer`, а для `quantity` — стандартная сериализация), и отображает десериализованные данные о товаре в `NavDisplay`.

## Ключевые концепции

1. **`DeepLinkSerializer<T>`**:
   Реализация `DeepLinkSerializer<T>` для обеспечения пользовательской логики строковой сериализации и десериализации объектов или типов (например, `Color` или упакованных строковых форматов), которые необходимо извлекать из параметров пути/запроса URI или кодировать в них.

2. **Аннотирование свойств `NavKey` с помощью `@Serializable(with = ...)`**:
   В пользовательском `NavKey` для свойств, типы которых требуют нестандартной сериализации (например, `product: Product`), используется аннотация `@Serializable(with = ProductSerializer::class)`.

3. **Сочетание стандартной и пользовательской сериализации параметров**:
   Параметры стандартных типов (например, `quantity: Int`) сериализуются автоматически средствами Kotlinx Serialization, тогда как для пользовательских параметров (например, `product: Product`) применяется `ProductSerializer`.

4. **Интеграция с `UriDeepLinkMatcher`**:
   `UriDeepLinkMatcher(uriPattern, serializer<ProductDetailsKey>())` автоматически применяет как пользовательские, так и стандартные сериализаторы при сопоставлении и декодировании URI диплинков.