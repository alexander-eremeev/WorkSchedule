/*
 * Copyright 2025 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.childmathematics.android.workschedule.navigation.conditional

import androidx.navigation3.runtime.NavBackStack

/**
 * Provides navigation events with built-in support for conditional access. If the user attempts to
 * navigate to a [ConditionalNavKey] that requires login ([ConditionalNavKey.requiresLogin] is true)
 * but is not currently logged in, the Navigator will redirect the user to a login key.
 *
 * @property backStack The back stack that is modified by this class
 * @property onNavigateToRestrictedKey A lambda that is called when the user attempts to navigate
 * to a key that requires login. This should return the key that represents the login screen. The
 * user's target key is supplied as a parameter so that after successful login the user can be
 * redirected to their target destination.
 * @property isLoggedIn A lambda that returns whether the user is logged in.
 * Обеспечивает обработку событий навигации со встроенной поддержкой условного доступа. Если пользователь пытается
 * * перейти по ключу [ConditionalNavKey], требующему авторизации (свойство [ConditionalNavKey.requiresLogin] имеет значение true),
 * * но не авторизован, Navigator перенаправит его на ключ экрана входа.
 * *
 * * @property backStack Стек навигации, изменяемый данным классом.
 * * @property onNavigateToRestrictedKey Лямбда-выражение, вызываемое при попытке пользователя перейти
 * * по ключу, требующему авторизации. Оно должно возвращать ключ, соответствующий экрану входа.
 * * Целевой ключ пользователя передается в качестве параметра, чтобы после успешной авторизации
 * * пользователя можно было перенаправить к исходной цели.
 * * @property isLoggedIn Лямбда-выражение, возвращающее статус авторизации пользователя.
 */
class Navigator(
    private val backStack: NavBackStack<ConditionalNavKey>,
    private val onNavigateToRestrictedKey: (targetKey: ConditionalNavKey?) -> ConditionalNavKey,
    private val isLoggedIn: () -> Boolean,
) {
    fun navigate(key: ConditionalNavKey) {
        if (key.requiresLogin && !isLoggedIn()) {
            val loginKey = onNavigateToRestrictedKey(key)
            backStack.add(loginKey)
        } else {
            backStack.add(key)
        }
    }

    fun goBack() = backStack.removeLastOrNull()
}