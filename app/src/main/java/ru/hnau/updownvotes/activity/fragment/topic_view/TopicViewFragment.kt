package ru.hnau.updownvotes.activity.fragment.topic_view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.data.TopicsManager


class TopicViewFragment : Fragment() {

    companion object {

        private const val ARG_TOPIC_ID_KEY = "ru.hnau.updownvotes.activity.fragment.topic_view.TopicViewFragment.topicId"

        fun newInstance(topicId: Long) =
                TopicViewFragment().apply {
                    arguments = Bundle().apply {
                        putLong(ARG_TOPIC_ID_KEY, topicId)
                    }
                }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val topic = getTopic() ?: return null
        return TopicViewFragmentView(inflater.context, topic)
    }

    private fun getTopic(): Topic? {
        val topicId = arguments?.getLong(ARG_TOPIC_ID_KEY) ?: return null
        return TopicsManager.getTopicById(topicId)
    }

}