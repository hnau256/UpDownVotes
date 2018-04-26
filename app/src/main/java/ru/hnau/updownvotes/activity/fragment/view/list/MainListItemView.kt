package ru.hnau.updownvotes.activity.fragment.view.list

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import ru.hnau.updownvotes.R
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.data.TopicRatingInfo
import ru.hnau.updownvotes.producer.detacher.ProducerDetachers
import ru.hnau.updownvotes.utils.UiUtils


class MainListItemView(context: Context) : LinearLayout(context) {

    var topic: Topic? = null
        set(value) {
            if (field != value && value != null) {
                field = value
                onTopicChanged(value)
            }
        }

    private val detachers = ProducerDetachers()

    private val upVotesCountView = TextView(context).apply {
        setTextColor(ContextCompat.getColor(context, R.color.vote_up))
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12f)
    }

    private val downVotesCountView = TextView(context).apply {
        setTextColor(ContextCompat.getColor(context, R.color.vote_down))
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12f)
    }

    private fun onTopicChanged(topic: Topic) {
        detachers.detachAllAndClear()
        attachToTopic(topic)
    }

    private fun attachToTopic(topic: Topic? = this.topic) =
            topic?.attach(detachers, this::onTopicRatingInfoChanged)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        attachToTopic()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        detachers.detachAllAndClear()
    }

    private fun onTopicRatingInfoChanged(topicRatingInfo: TopicRatingInfo) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
                MeasureSpec.makeMeasureSpec(UiUtils.getMaxSize(0, widthMeasureSpec), MeasureSpec.EXACTLY),
                heightMeasureSpec
        )
    }

}