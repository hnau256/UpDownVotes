package ru.hnau.updownvotes.activity.fragment.add_topic

import android.content.Context
import ru.hnau.updownvotes.activity.fragment.MainFragment
import ru.hnau.updownvotes.producer.detacher.ProducerDetachers

/**
 * Фрагмент добавления темы
 */

class AddTopicFragment : MainFragment() {

    private val detachers = ProducerDetachers()

    override fun createView(context: Context) =
            AddTopicFragmentView(context).apply {
                onTopicAddedProducer.attach(detachers, {
                    mainActivity?.goBack()
                })
            }

    override fun onDestroyView() {
        super.onDestroyView()
        detachers.detachAllAndClear()
    }

}