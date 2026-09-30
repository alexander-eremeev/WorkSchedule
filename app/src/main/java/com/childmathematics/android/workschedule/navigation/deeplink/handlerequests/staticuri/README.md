# Deep Link Static URI Recipe

This recipe demonstrates how deep link with a static Uri.

## Recipe components

The recipe contains two activities:
1. `StaticUriDeepLinkActivity` to construct and start an Intent with the deep link uri 
2. `MainActivity` is the target Activity of the deep link, represents an app that users can deep link to.

## How the demonstrated deep link works

1. The deep link source (`StaticUriDeepLinkActivity`) defines the uri and creates an Intent to deep link with.
2. The app (`MainActivity`) declares a navigation key (`HomeKey`). To indicate that `HomeKey` supports deep linking, the app declares a `UriDeepLinkMatcher` with the `HomeKey` serializer along with the uri pattern that `HomeKey` supports.
3. `MainActivity` onCreate instantiates a `DeepLinkRequest` with the intent and matches it with the `UriDeepLinkMatcher` to get a `MatchResult`. If the `MatchResult` is non-null, the app navigates to the key returned by the result. Otherwise, the deep link is not supported and the app navigates to a `Fallback` screen.

# Пример использования статического URI для диплинка

В этом примере показано, как реализовать диплинк (глубокую ссылку) с использованием статического URI.

## Компоненты примера

Пример включает две Activity:
1. `StaticUriDeepLinkActivity` — отвечает за формирование и запуск Intent с URI диплинка.
2. `MainActivity` — целевая Activity для диплинка; она представляет собой экран приложения, на который переходит пользователь.

## Принцип работы показанного диплинка

1. Источник диплинка (`StaticUriDeepLinkActivity`) определяет URI и создает Intent для перехода по ссылке.
2. Приложение (`MainActivity`) объявляет ключ навигации (`HomeKey`). Чтобы указать, что `HomeKey` поддерживает диплинки, приложение объявляет `UriDeepLinkMatcher`, используя сериализатор для `HomeKey` и шаблон URI, поддерживаемый этим ключом.
3. В методе `onCreate` класса `MainActivity` создается объект `DeepLinkRequest` на основе полученного Intent; затем он сопоставляется с `UriDeepLinkMatcher` для получения результата `MatchResult`. Если `MatchResult` не равен null, приложение выполняет переход к ключу, указанному в результате. В противном случае (если диплинк не поддерживается) приложение переходит на экран-заглушку (`Fallback`).