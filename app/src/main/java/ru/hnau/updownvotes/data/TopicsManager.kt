package ru.hnau.updownvotes.data

import ru.hnau.updownvotes.producer.CallOnAttachProducer


object TopicsManager : CallOnAttachProducer<List<Topic>>() {

    private const val TOPICS_IN_LIST = 20

    private val topics = ArrayList<Topic>()

    override fun getData() =
            topics.sortedByDescending { it.upCount }.take(TOPICS_IN_LIST)

    fun addTopic(topic: Topic) {
        topics.add(topic)
        call()
    }


}