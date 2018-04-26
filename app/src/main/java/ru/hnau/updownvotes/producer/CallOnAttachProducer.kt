package ru.hnau.updownvotes.producer

import ru.hnau.updownvotes.producer.listener.ProducerListener


abstract class CallOnAttachProducer<T> : Producer<T>() {

    override fun onAttached(listener: ProducerListener<T>) {
        super.onAttached(listener)
        listener.invoke(getData())
    }

    protected abstract fun getData(): T

    fun call() = call(getData())

}