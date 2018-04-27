package ru.hnau.updownvotes.data

import ru.hnau.updownvotes.producer.CallOnAttachProducer

/**
 * Список тем
 */

object TopicsManager : CallOnAttachProducer<List<Long>>() {

    private const val TOPICS_IN_LIST = 20

    private var lastAddedId = -1L

    private val topicsList = HashMap<Long, Topic>()

    override fun getData() =
            topicsList.entries.sortedByDescending { it.value.sortOrder }.take(TOPICS_IN_LIST).map { it.key }

    fun addTopic(topicText: String) {
        synchronized(this, {
            val topic = createTopic(topicText)
            topicsList[topic.id] = topic
            lastAddedId = topic.id
        })
        call()
    }

    private fun createTopic(topicText: String): Topic {
        val id = lastAddedId + 1
        return Topic(id, topicText)
    }

    fun getTopicById(id: Long) = topicsList[id]


}