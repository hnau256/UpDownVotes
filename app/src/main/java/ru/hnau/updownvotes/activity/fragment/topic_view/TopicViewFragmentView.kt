package ru.hnau.updownvotes.activity.fragment.topic_view

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import ru.hnau.updownvotes.R
import ru.hnau.updownvotes.activity.view.TopicRatingInfoView
import ru.hnau.updownvotes.activity.view.TopicUpDownButtonsView
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.utils.UiUtils

@SuppressLint("ViewConstructor")
class TopicViewFragmentView(
        context: Context,
        topic: Topic
) : LinearLayout(
        context
) {

    private val ratingInfoView = TopicRatingInfoView(context).apply {
        this.topic = topic
    }

    private val upDownButtonsView = TopicUpDownButtonsView(context).apply {
        this.topic = topic
    }

    private val headerView = LinearLayout(context).apply {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        setBackgroundColor(ContextCompat.getColor(context, R.color.bg))

        addView(ratingInfoView)
        addView(View(context).apply { layoutParams = LinearLayout.LayoutParams(0, 0, 1f) })
        addView(upDownButtonsView)
    }

    private val topicTextView = TextView(context).apply {
        layoutParams = ViewGroup.LayoutParams(UiUtils.MATCH_PARENT, UiUtils.WRAP_CONTENT)
        text = topic.text
        setTextColor(ContextCompat.getColor(context, R.color.fg))
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
        val paddingV = UiUtils.dpToPx(8f, context).toInt()
        val paddingH = UiUtils.dpToPx(16f, context).toInt()
        setPadding(paddingH, paddingV, paddingH, paddingV)
    }

    private val topicTextScrollView = ScrollView(context).apply {
        layoutParams = LinearLayout.LayoutParams(UiUtils.MATCH_PARENT, 0, 1f)
        addView(topicTextView)
    }

    init {

        orientation = VERTICAL
        addView(headerView)
        addView(topicTextScrollView)

    }

}