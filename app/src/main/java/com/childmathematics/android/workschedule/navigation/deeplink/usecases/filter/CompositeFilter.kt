package com.childmathematics.android.workschedule.navigation.deeplink.usecases.filter

import androidx.navigation3.runtime.deeplink.DeepLinkMatcher.Filter

/**
 * Combines this [Filter] with [other] using logical AND.
 *
 * Short-circuits evaluation if this filter returns `false`.
 *
 * **Example:**
 * ```kotlin
 * val compositeFilter = actionFilter(Intent.ACTION_VIEW) and mimeTypeFilter("image/png")
 * Объединяет этот [Фильтр] с [другим] с помощью логического И.
 *
 * *
 *
 * * Прерывает вычисление, если этот фильтр возвращает `false`.
 *
 * *
 *
 * * **Пример:**
 *
 * * ```kotlin
 * * val compositeFilter = actionFilter(Intent.ACTION_VIEW) and mimeTypeFilter("image/png")
 * ```
 */
infix fun Filter.and(other: Filter): Filter = Filter { request ->
    filterRequest(request) && other.filterRequest(request)
}

/**
 * Combines this [Filter] with [other] using logical OR.
 *
 * Short-circuits evaluation if this filter returns `true`.
 *
 * **Example:**
 * ```kotlin
 * val imageFilter = mimeTypeFilter("image/png") or mimeTypeFilter("image/jpeg")
 * Объединяет этот [фильтр] с [другим] с помощью логического ИЛИ.
 *
 * *
 *
 * * Прерывает вычисление, если этот фильтр возвращает `true`.
 *
 * *
 *
 * * **Пример:**
 *
 * * ```kotlin
 * * val imageFilter = mimeTypeFilter("image/png") or mimeTypeFilter("image/jpeg")
 * ```
 */
infix fun Filter.or(other: Filter): Filter = Filter { request ->
    filterRequest(request) || other.filterRequest(request)
}

/**
 * Inverts the result of this [Filter] using logical NOT.
 *
 * **Example:**
 * ```kotlin
 * val nonPdfFilter = !mimeTypeFilter("application/pdf")
 * Инвертирует результат этого [фильтра] с помощью логического НЕ.
 *
 * *
 *
 * * **Пример:**
 *
 * * ```kotlin
 * * val nonPdfFilter = !mimeTypeFilter("application/pdf")
 * ```
 */
operator fun Filter.not(): Filter = Filter { request ->
    !filterRequest(request)
}

