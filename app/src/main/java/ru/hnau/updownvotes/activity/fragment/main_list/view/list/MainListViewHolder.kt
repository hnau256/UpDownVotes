package ru.hnau.updownvotes.activity.fragment.main_list.view.list

import android.support.v7.widget.RecyclerView
import ru.hnau.updownvotes.data.TopicsManager
import ru.hnau.updownvotes.producer.Producer


class MainListViewHolder(
        private val mainListItemView: MainListItemView
) : RecyclerView.ViewHolder(
        mainListItemView
) {

    fun setTopicById(topicId: Long, onTopicClickedProducer: Producer<Long>) {
        val topic = TopicsManager.getTopicById(topicId) ?: return
        mainListItemView.topic = topic
        mainListItemView.setOnClickListener { onTopicClickedProducer.call(topicId) }
    }

}