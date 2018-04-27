package ru.hnau.updownvotes.activity.fragment.main_list.view.list

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import ru.hnau.updownvotes.R
import ru.hnau.updownvotes.activity.view.TopicRatingInfoView
import ru.hnau.updownvotes.activity.view.TopicUpDownButtonsView
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.producer.detacher.ProducerDetachers
import ru.hnau.updownvotes.utils.UiUtils

/**
 *
 * Элемент списка главного экрана
 *
 */

class MainListItemView(context: Context) : LinearLayout(context) {

    var topic: Topic? = null
        set(value) {
            if (field != value && value != null) {
                field = value
                onTopicChanged(value)
            }
        }

    private val detachers = ProducerDetachers()

    private val topicRatingInfoView = TopicRatingInfoView(context)

    private val titleView = TextView(context).apply {
        setTextColor(ContextCompat.getColor(context, R.color.fg))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        maxLines = 4
        minLines = 1
        layoutParams = LinearLayout.LayoutParams(0, UiUtils.WRAP_CONTENT, 1f)

        val paddingV = UiUtils.dpToPx(4f, context).toInt()
        setPadding(0, paddingV, UiUtils.dpToPx(8f, context).toInt(), paddingV)
        ellipsize = TextUtils.TruncateAt.END
    }

    private val topicUpDownButtonsView = TopicUpDownButtonsView(context)

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        setBackgroundColor(ContextCompat.getColor(context, R.color.bg))

        addView(topicRatingInfoView)
        addView(titleView)
        addView(topicUpDownButtonsView)
    }

    private fun onTopicChanged(topic: Topic) {
        detachers.detachAllAndClear()
        titleView.text = topic.text
        topicRatingInfoView.topic = topic
        topicUpDownButtonsView.topic = topic
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
                MeasureSpec.makeMeasureSpec(UiUtils.getMaxSize(0, widthMeasureSpec), MeasureSpec.EXACTLY),
                heightMeasureSpec
        )
    }

}