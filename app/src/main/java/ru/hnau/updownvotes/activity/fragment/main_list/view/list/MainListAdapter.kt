package ru.hnau.updownvotes.activity.fragment.main_list.view.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.data.TopicsManager
import ru.hnau.updownvotes.producer.Producer
import ru.hnau.updownvotes.producer.detacher.ProducerDetachers

/**
 *
 * Адаплер списка тем
 *
 */

class MainListAdapter(
        private val context: Context
) : RecyclerView.Adapter<MainListViewHolder>() {

    private var topicsIds: List<Long> = emptyList()

    private val detachers = ProducerDetachers()

    val onTopicClickedProducer = Producer<Long>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MainListViewHolder(MainListItemView(context))

    override fun getItemCount() = topicsIds.size

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val topicId = topicsIds[position]
        holder.setTopicById(topicId, onTopicClickedProducer)
    }

    //Список тем изменился
    private fun onTopicsIdsChanged(topics: List<Long>) {
        this.topicsIds = topics
        notifyDataSetChanged()
    }

    //Подписка на обновление списка тем
    fun onAttachedToWindow() = TopicsManager.attach(detachers, this::onTopicsIdsChanged)

    fun onDetachedFromWindow() = detachers.detachAllAndClear()

}