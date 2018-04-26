package ru.hnau.updownvotes.producer

import ru.hnau.updownvotes.producer.detacher.ProducerDetacher
import ru.hnau.updownvotes.producer.detacher.ProducerDetachers
import ru.hnau.updownvotes.producer.listener.ProducerListener

open class Producer<T> {

    private val listeners = HashSet<ProducerListener<T>>()

    fun attach(listener: ProducerListener<T>) = synchronized(this) {
        val first = listeners.isEmpty()
        val attached = listeners.add(listener)

        if (!attached) {
            return@synchronized ProducerDetacher(this)
        }

        if (first) {
            onFirstAttached(listener)
        }
        onAttached(listener)

        return@synchronized ProducerDetacher(this, listener)
    }

    fun attach(detachers: ProducerDetachers, listener: ProducerListener<T>) = detachers.add(attach(listener))

    fun detach(listener: ProducerListener<T>) = synchronized(this) {
        val detached = listeners.remove(listener)
        if (!detached) {
            return@synchronized
        }

        onDetached(listener)
        if (listeners.isEmpty()) {
            onLastDetached(listener)
        }
    }

    fun call(data: T) = synchronized(this) {
        listeners.forEach { it.invoke(data) }
    }

    open fun onFirstAttached(listener: ProducerListener<T>) {}

    open fun onAttached(listener: ProducerListener<T>) {}

    open fun onLastDetached(listener: ProducerListener<T>) {}

    open fun onDetached(listener: ProducerListener<T>) {}


}