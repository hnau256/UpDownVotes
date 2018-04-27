package ru.hnau.updownvotes.activity.view

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import ru.hnau.updownvotes.R
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.data.TopicRatingInfo
import ru.hnau.updownvotes.producer.detacher.ProducerDetachers
import ru.hnau.updownvotes.utils.UiUtils

/**
 *
 * View рейтинга темы показывается количество плюсов и минусов и общий ретинг
 *
 */

class TopicRatingInfoView(context: Context) : LinearLayout(context) {

    companion object {

        private const val UP_DOWN_VOTES_TEXT_SIZE_SP = 10f

    }

    private val upVotesCountView = createRatingInfoView(R.color.vote_up, UP_DOWN_VOTES_TEXT_SIZE_SP)

    private val ratingView = createRatingInfoView(R.color.fg, 16f)

    private val downVotesCountView = createRatingInfoView(R.color.vote_down, UP_DOWN_VOTES_TEXT_SIZE_SP)

    private val detachers = ProducerDetachers()

    var topic: Topic? = null
        set(value) {
            if (field != value && value != null) {
                field = value
                onTopicChanged(value)
            }
        }

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER

        val paddingV = UiUtils.dpToPx(8f, context).toInt()
        val paddingH = UiUtils.dpToPx(16f, context).toInt()
        setPadding(paddingH, paddingV, paddingH, paddingV)

        layoutParams = LinearLayout.LayoutParams(UiUtils.dpToPx(64f, context).toInt(), UiUtils.WRAP_CONTENT)

        addView(upVotesCountView)
        addView(ratingView)
        addView(downVotesCountView)
    }

    private fun onTopicChanged(topic: Topic) = reattachToTopic(topic)


    // Подписка на изменение ретинга темы (происходит при изменении ассоциированной темы или при показе на экране)
    private fun reattachToTopic(topic: Topic? = this.topic) {
        detachers.detachAllAndClear()
        topic?.attach(detachers, this::onTopicRatingInfoChanged)
    }

    private fun onTopicRatingInfoChanged(topicRatingInfo: TopicRatingInfo) {
        upVotesCountView.text = topicRatingInfo.upVotes.toString()
        downVotesCountView.text = topicRatingInfo.downVotes.toString()
        ratingView.text = topicRatingInfo.rating.toString()
    }

    private fun createRatingInfoView(colorResId: Int, textSizeSp: Float) = TextView(context).apply {
        setTextColor(ContextCompat.getColor(context, colorResId))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSp)
        maxLines = 1
        minLines = 1
        gravity = Gravity.CENTER
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        reattachToTopic()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        detachers.detachAllAndClear()
    }

}