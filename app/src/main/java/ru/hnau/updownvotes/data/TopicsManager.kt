package ru.hnau.updownvotes.data

import ru.hnau.updownvotes.producer.CallOnAttachProducer

/**
 * Список тем
 */

object TopicsManager : CallOnAttachProducer<List<Topic>>() {

    private const val TOPICS_IN_LIST = 20

    private val topicsList = ArrayList<Topic>()

    override fun getData() =
            topicsList.sortedByDescending { it.sortOrder }.take(TOPICS_IN_LIST)

    fun addTopic(topic: Topic) {
        topicsList.add(topic)
        call()
    }

    init {

        addTopic(Topic("Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 Topic1 "))
        addTopic(Topic("Topic2"))
        addTopic(Topic("Topic3"))

    }


}