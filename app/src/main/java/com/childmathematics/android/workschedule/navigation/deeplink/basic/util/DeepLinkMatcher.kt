package com.childmathematics.android.workschedule.navigation.deeplink.basic.util

import android.util.Log
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.KSerializer

internal class DeepLinkMatcher<T : NavKey>(
    val request: DeepLinkRequest,
    val deepLinkPattern: DeepLinkPattern<T>
) {
    /**
     * Match a [DeepLinkRequest] to a [DeepLinkPattern].
     *
     * Returns a [DeepLinkMatchResult] if this matches the pattern, returns null otherwise
     *
     * Сопоставляет [DeepLinkRequest] с [DeepLinkPattern].
     * *
     * * Возвращает [DeepLinkMatchResult], если запрос соответствует шаблону, или null в противном случае.
     */
    fun match(): DeepLinkMatchResult<T>? {
        if (request.pathSegments.size != deepLinkPattern.pathSegments.size) return null
        // exact match (url does not contain any arguments)
        if (request.uri == deepLinkPattern.uriPattern)
            return DeepLinkMatchResult(deepLinkPattern.serializer, mapOf())

        val args = mutableMapOf<String, Any>()
        // match the path
        request.pathSegments
            .asSequence()
            // zip to compare the two objects side by side, order matters here so we
            // need to make sure the compared segments are at the same position within the url
            // используем zip для попарного сравнения двух объектов; здесь важен порядок, поэтому
            //// нужно убедиться, что сравниваемые сегменты находятся на одной и той же позиции внутри URL
            .zip(deepLinkPattern.pathSegments.asSequence())
            .forEach { it ->
                // retrieve the two path segments to compare
                val requestedSegment = it.first
                val candidateSegment = it.second
                // if the potential match expects a path arg for this segment, try to parse the
                // requested segment into the expected type
                if (candidateSegment.isParamArg) {
                    val parsedValue = try {
                        candidateSegment.typeParser.invoke(requestedSegment)
                    } catch (e: IllegalArgumentException) {
                        Log.e(TAG_LOG_ERROR, "Failed to parse path value:[$requestedSegment].", e)
                        return null
                    }
                    args[candidateSegment.stringValue] = parsedValue
                } else if(requestedSegment != candidateSegment.stringValue){
                    // if it's path arg is not the expected type, its not a match
                    return null
                }
            }
        // match queries (if any)
        request.queries.forEach { query ->
            val name = query.key
            val queryStringParser = deepLinkPattern.queryValueParsers[name]
            val queryParsedValue = try {
                queryStringParser!!.invoke(query.value)
            } catch (e: IllegalArgumentException) {
                Log.e(TAG_LOG_ERROR, "Failed to parse query name:[$name] value:[${query.value}].", e)
                return null
            }
            args[name] = queryParsedValue
        }
        // provide the serializer of the matching key and map of arg names to parsed arg values
        return DeepLinkMatchResult(deepLinkPattern.serializer, args)
    }
}


/**
 * Created when a requested deeplink matches with a supported deeplink
 *
 * @param [T] the backstack key associated with the deeplink that matched with the requested deeplink
 * @param serializer serializer for [T]
 * @param args The map of argument name to argument value. The value is expected to have already
 * been parsed from the raw url string back into its proper KType as declared in [T].
 * Includes arguments for all parts of the uri - path, query, etc.
 * Создается, когда запрошенная глубокая ссылка (deeplink) совпадает с поддерживаемой глубокой ссылкой.
 * *
 * * @param [T] ключ стека переходов (backstack key), связанный с глубокой ссылкой, которая совпала с запрошенной
 * * @param serializer сериализатор для [T]
 * * @param args Карта, сопоставляющая имена аргументов с их значениями. Предполагается, что значения
 * * уже были преобразованы из исходной строки URL в соответствующий тип KType, как определено в [T].
 * * Включает аргументы для всех частей URI: пути, параметров запроса (query) и т. д.
 * */
internal data class DeepLinkMatchResult<T : NavKey>(
    val serializer: KSerializer<T>,
    val args: Map<String, Any>
)

const val TAG_LOG_ERROR = "Nav3RecipesDeepLink"