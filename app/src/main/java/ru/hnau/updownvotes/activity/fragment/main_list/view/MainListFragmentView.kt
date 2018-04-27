package ru.hnau.updownvotes.activity.fragment.main_list.view

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import ru.hnau.updownvotes.R
import ru.hnau.updownvotes.activity.fragment.main_list.view.list.MainList
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.data.TopicsManager
import ru.hnau.updownvotes.producer.Producer
import ru.hnau.updownvotes.producer.detacher.ProducerDetachers
import ru.hnau.updownvotes.utils.UiUtils


class MainListFragmentView(context: Context) : FrameLayout(context) {

    val onAddTopicButtonClickedProducer = Producer<Unit>()

    val onTopicClickedProducer: Producer<Long>
        get() = mainList.onTopicClickedProducer

    private val detachers = ProducerDetachers()

    private val mainList: MainList by lazy {
        MainList(context).apply {
            layoutParams = FrameLayout.LayoutParams(UiUtils.MATCH_PARENT, UiUtils.MATCH_PARENT)
        }
    }

    private val noTopicsView: View by lazy {
        TextView(context).apply {
            text = context.getString(R.string.main_list_empty_info)
            gravity = Gravity.CENTER
            val padding = UiUtils.dpToPx(32f, context).toInt()
            setPadding(padding, padding, padding, padding)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            setTextColor(ContextCompat.getColor(context, R.color.fg_50))
        }
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

    private fun onTopicsChanged(topicsIds: List<Long>) {
        removeAllViews()
        val contentView = if (topicsIds.isEmpty()) noTopicsView else mainList
        addView(contentView)
        addView(addTopicButton)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        TopicsManager.attach(detachers, this::onTopicsChanged)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        detachers.detachAllAndClear()
    }


}