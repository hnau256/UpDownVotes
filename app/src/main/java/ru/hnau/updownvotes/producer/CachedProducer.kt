package ru.hnau.updownvotes.producer


open class CachedProducer<T>(
        initialData: T
) : CallOnAttachProducer<T>() {

    protected var cachedData = initialData
        set(value) {
            field = value
            call(value)
        }

    override fun getData() = cachedData


}