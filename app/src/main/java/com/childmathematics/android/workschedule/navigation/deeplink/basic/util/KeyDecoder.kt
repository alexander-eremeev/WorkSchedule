package com.childmathematics.android.workschedule.navigation.deeplink.basic.util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

/**
 * Decodes the list of arguments into a a back stack key
 *
 * **IMPORTANT** This decoder assumes that all argument types are Primitives.
 * Декодирует список аргументов в ключ стека возврата.
 * *
 * * **ВАЖНО:** Данный декодер предполагает, что все типы аргументов являются примитивными.
 */
@OptIn(ExperimentalSerializationApi::class)
internal class KeyDecoder(
    private val arguments: Map<String, Any>,
) : AbstractDecoder() {

    override val serializersModule: SerializersModule = EmptySerializersModule()
    private var elementIndex: Int = -1
    private var elementName: String = ""

    /**
     * Decodes the index of the next element to be decoded. Index represents a position of the
     * current element in the [descriptor] that can be found with [descriptor].getElementIndex.
     *
     * The returned index will trigger deserializer to call [decodeValue] on the argument at that
     * index.
     *
     * The decoder continually calls this method to process the next available argument until this
     * method returns [CompositeDecoder.DECODE_DONE], which indicates that there are no more
     * arguments to decode.
     *
     * This method should sequentially return the element index for every element that has its value
     * available within [arguments].
     * Декодирует индекс следующего элемента, подлежащего декодированию. Индекс представляет собой позицию
     * * текущего элемента в [descriptor]; эту позицию можно определить с помощью [descriptor].getElementIndex.
     * *
     * * Возвращенный индекс побудит десериализатор вызвать [decodeValue] для аргумента,
     * * соответствующего этому индексу.
     * *
     * * Декодер многократно вызывает этот метод для обработки следующего доступного аргумента,
     * * пока метод не вернет [CompositeDecoder.DECODE_DONE], что означает отсутствие
     * * дополнительных аргументов для декодирования.
     * *
     * * Этот метод должен последовательно возвращать индекс для каждого элемента,
     * * значение которого доступно в [arguments].
     */
    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        var currentIndex = elementIndex
        while (true) {
            // proceed to next element
            currentIndex++
            // if we have reached the end, let decoder know there are not more arguments to decode
            if (currentIndex >= descriptor.elementsCount) return CompositeDecoder.DECODE_DONE
            val currentName = descriptor.getElementName(currentIndex)
            // Check if bundle has argument value. If so, we tell decoder to process
            // currentIndex. Otherwise, we skip this index and proceed to next index.
            if (arguments.contains(currentName)) {
                elementIndex = currentIndex
                elementName = currentName
                return elementIndex
            }
        }
    }

    /**
     * Returns argument value from the [arguments] for the argument at the index returned by
     * [decodeElementIndex]
     * Возвращает значение аргумента из [arguments], соответствующее индексу, возвращенному методом [decodeElementIndex].
     */
    override fun decodeValue(): Any {
        val arg = arguments[elementName]
        checkNotNull(arg) { "Unexpected null value for non-nullable argument $elementName" }
        return arg
    }

    override fun decodeNull(): Nothing? = null

    // we want to know if it is not null, so its !isNull
    override fun decodeNotNullMark(): Boolean = arguments[elementName] != null
}