package ru.hnau.updownvotes.data

import ru.hnau.updownvotes.producer.CallOnAttachProducer

/**
 * Список тем
 */

object TopicsManager : CallOnAttachProducer<List<Long>>() {

    private const val TOPICS_IN_LIST = 20

    private val topicsList = HashMap<Long, Topic>()

    override fun getData() =
            topicsList.entries.sortedByDescending { it.value.sortOrder }.take(TOPICS_IN_LIST).map { it.key }

    fun addTopic(topic: Topic) {
        topicsList[topic.id] = topic
        call()
    }

    fun getTopicById(id: Long) = topicsList[id]

    init {

        addTopic(Topic(0, "Topic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\nTopic1\n"))
        addTopic(Topic(1, "Topic2"))
        addTopic(Topic(2, "Topic3"))

    }


}