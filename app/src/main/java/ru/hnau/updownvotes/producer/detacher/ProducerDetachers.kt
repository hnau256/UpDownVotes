package ru.hnau.updownvotes.producer.detacher

import java.util.*


class ProducerDetachers {

    private val detachers = LinkedList<ProducerDetacher<*>>()

    fun add(detacher: ProducerDetacher<*>) {
        detachers.add(detacher)
    }

    fun detachAllAndClear() = synchronized(this, {
        detachers.forEach(ProducerDetacher<*>::detach)
        detachers.clear()
    })

}