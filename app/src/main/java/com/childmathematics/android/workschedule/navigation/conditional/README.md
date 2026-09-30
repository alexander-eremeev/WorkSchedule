# Conditional Navigation Recipe

This recipe demonstrates how to implement conditional navigation, where certain destinations are only accessible if a condition is met (in this case, if the user is logged in).

## How it works

This example has a `Profile` destination that requires the user to be logged in. If the user is not logged in and attempts to navigate to `Profile`, they are redirected to a `Login` screen. After a successful login, they are automatically navigated to the `Profile` screen.

### `AppBackStack`

The core of this recipe is the custom `AppBackStack` class, which encapsulates the logic for conditional navigation.

-   **`RequiresLogin` interface**: A marker interface, `RequiresLogin`, is used to identify destinations that require the user to be logged in. The `Profile` destination implements this interface.

-   **Redirecting to Login**: When the `add` function is called with a destination that implements `RequiresLogin` and the user is not logged in, `AppBackStack` stores the intended destination and adds the `Login` route to the back stack instead.

-   **Handling Login**: When the `login` function is called, it sets the user's status to logged in. If there is a stored destination that the user was trying to access, it adds that destination to the back stack and removes the `Login` screen.

-   **Handling Logout**: When the `logout` function is called, it sets the user's status to logged out and removes any destinations from the back stack that require the user to be logged in.

This approach provides a clean way to handle conditional navigation by centralizing the logic in a custom back stack implementation.

# Рецепт условной навигации

В этом примере показано, как реализовать условную навигацию, при которой определенные экраны доступны 
только при выполнении заданного условия (в данном случае — если пользователь авторизован).

## Принцип работы

В этом примере есть экран `Profile`, доступ к которому возможен только после входа в систему. Если 
неавторизованный пользователь пытается перейти на экран `Profile`, он перенаправляется на экран `Login`. 
После успешного входа происходит автоматический переход на экран `Profile`.

### `AppBackStack`

Основой этого решения является пользовательский класс `AppBackStack`, инкапсулирующий логику условной навигации.

-   **Интерфейс `RequiresLogin`**: Это интерфейс-маркер, используемый для обозначения экранов, требующих 
- авторизации пользователя. Экран `Profile` реализует этот интерфейс.

-   **Перенаправление на экран входа**: При вызове функции `add` с экраном, реализующим `RequiresLogin`, если 
пользователь не авторизован, `AppBackStack` сохраняет целевой экран, а в стек навигации вместо него добавляет маршрут `Login`.

-   **Обработка входа**: При вызове функции `login` статус пользователя меняется на «авторизован». Если в памяти 
сохранен целевой экран, к которому пытался перейти пользователь, этот экран добавляется в стек навигации, а экран `Login` удаляется.

-   **Обработка выхода**: При вызове функции `logout` статус пользователя меняется на «не авторизован», 
а из стека навигации удаляются все экраны, требующие авторизации.

Такой подход обеспечивает удобный способ управления условной навигацией за счет централизации логики 
в пользовательской реализации стека навигации.