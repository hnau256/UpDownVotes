package ru.hnau.updownvotes.activity.fragment.main_list

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.hnau.updownvotes.activity.MainActivity
import ru.hnau.updownvotes.activity.fragment.MainFragment
import ru.hnau.updownvotes.activity.fragment.main_list.view.MainListFragmentView
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.producer.detacher.ProducerDetachers

/**
 * Фрагмент главного списка
 */

class MainListFragment : MainFragment() {

    private val detachers = ProducerDetachers()

    override fun createView(context: Context): View? {
        val view = MainListFragmentView(context)
        view.onAddTopicButtonClickedProducer.attach(detachers, { onAddTopicButtonClicked() })
        view.onTopicClickedProducer.attach(detachers, this::onTopicClicked)
        return view
    }

    private fun onTopicClicked(topicId: Long) {
        mainActivity?.onTopicClicked(topicId)
    }

    private fun onAddTopicButtonClicked() = mainActivity?.onAddTopicButtonClicked()

    override fun onDestroyView() {
        super.onDestroyView()
        detachers.detachAllAndClear()
    }
}
