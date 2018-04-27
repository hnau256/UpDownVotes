package ru.hnau.updownvotes.activity.fragment.main_list.view

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import android.widget.FrameLayout
import ru.hnau.updownvotes.R
import ru.hnau.updownvotes.activity.fragment.main_list.view.list.MainList
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.producer.Producer
import ru.hnau.updownvotes.utils.UiUtils


class MainListFragmentView(context: Context) : FrameLayout(context) {

    val onAddTopicButtonClickedProducer = Producer<Unit>()

    val onTopicClickedProducer: Producer<Long>
        get() = mainList.onTopicClickedProducer

    private val mainList = MainList(context).apply {
        layoutParams = FrameLayout.LayoutParams(UiUtils.MATCH_PARENT, UiUtils.MATCH_PARENT)
    }

    private val addTopicButton = FloatingActionButton(context).apply {
        setOnClickListener { onAddTopicButtonClickedProducer.call(Unit) }
        setImageResource(R.drawable.ic_add_topic_button)
        layoutParams = FrameLayout.LayoutParams(UiUtils.WRAP_CONTENT, UiUtils.WRAP_CONTENT).apply {
            gravity = Gravity.END or Gravity.BOTTOM
            val rightBottomMargin = UiUtils.dpToPx(24f, context).toInt()
            rightMargin = rightBottomMargin
            bottomMargin = rightBottomMargin
        }
    }

    init {
        addView(mainList)
        addView(addTopicButton)
    }


}