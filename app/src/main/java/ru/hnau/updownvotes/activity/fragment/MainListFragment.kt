package ru.hnau.updownvotes.activity.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.hnau.updownvotes.activity.MainActivity
import ru.hnau.updownvotes.activity.fragment.view.MainListFragmentView
import ru.hnau.updownvotes.producer.detacher.ProducerDetachers

/**
 * Фрагмент главного списка
 */

class MainListFragment : Fragment() {

    private val detachers = ProducerDetachers()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = MainListFragmentView(inflater.context)
        view.onAddTopicButtonClickedProducer.attach(detachers, { onAddTopicButtonClicked() })
        return view
    }

    private fun onAddTopicButtonClicked() = (activity as? MainActivity)?.onAddTopicButtonClicked()

    override fun onDestroyView() {
        super.onDestroyView()
        detachers.detachAllAndClear()
    }
}
