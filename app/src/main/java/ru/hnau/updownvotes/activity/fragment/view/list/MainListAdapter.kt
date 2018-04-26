package ru.hnau.updownvotes.activity.fragment.view.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.data.TopicsManager
import ru.hnau.updownvotes.producer.detacher.ProducerDetachers

/**
 *
 * Адаплер списка тем
 *
 */

class MainListAdapter(
        private val context: Context
) : RecyclerView.Adapter<MainListViewHolder>() {

    private var topics: List<Topic> = emptyList()

    private val detachers = ProducerDetachers()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MainListViewHolder(MainListItemView(context))

    override fun getItemCount() = topics.size

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val topic = topics[position]
        holder.setTopic(topic)
    }

    //Список тем изменился
    private fun onTopicsChanged(topics: List<Topic>) {
        this.topics = topics
        notifyDataSetChanged()
    }

    //Подписка на обновление списка тем
    fun onAttachedToWindow() = TopicsManager.attach(detachers, this::onTopicsChanged)

    fun onDetachedFromWindow() = detachers.detachAllAndClear()

}