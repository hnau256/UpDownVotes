package ru.hnau.updownvotes.producer.detacher

import ru.hnau.updownvotes.producer.Producer
import ru.hnau.updownvotes.producer.listener.ProducerListener


class ProducerDetacher<T>(
        private val producer: Producer<T>,
        private val listener: ProducerListener<T>? = null
) {

    fun detach() {
        listener?.let { producer.detach(it) }
    }

}