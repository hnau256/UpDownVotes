package ru.hnau.updownvotes.activity.fragment.view.list

import android.support.v7.widget.RecyclerView
import ru.hnau.updownvotes.data.Topic


class MainListViewHolder(
        private val mainListItemView: MainListItemView
) : RecyclerView.ViewHolder(
        mainListItemView
) {

    fun setTopic(topic: Topic) {
        mainListItemView.topic = topic
    }

}