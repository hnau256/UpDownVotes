package ru.hnau.updownvotes.activity.fragment.topic_view

import android.content.Context
import android.os.Bundle
import android.view.View
import ru.hnau.updownvotes.activity.fragment.MainFragment
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.data.TopicsManager


class TopicViewFragment : MainFragment() {

    companion object {

        private const val ARG_TOPIC_ID_KEY = "ru.hnau.updownvotes.activity.fragment.topic_view.TopicViewFragment.topicId"

        fun newInstance(topicId: Long) =
                TopicViewFragment().apply {
                    arguments = Bundle().apply {
                        putLong(ARG_TOPIC_ID_KEY, topicId)
                    }
                }

    }

    override fun createView(context: Context): View? {
        val topic = getTopic() ?: return null
        return TopicViewFragmentView(context, topic)
    }

    private fun getTopic(): Topic? {
        val topicId = arguments?.getLong(ARG_TOPIC_ID_KEY) ?: return null
        return TopicsManager.getTopicById(topicId)
    }

}