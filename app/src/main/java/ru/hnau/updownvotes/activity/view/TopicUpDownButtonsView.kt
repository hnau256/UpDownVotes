package ru.hnau.updownvotes.activity.view

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import ru.hnau.updownvotes.R
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.utils.UiUtils


class TopicUpDownButtonsView(context: Context) : LinearLayout(context) {

    var topic: Topic? = null

    private val voteUpButton = createVoteButton(R.drawable.ic_voute_up, { topic?.onUpVote() })

    private val voteDownButton = createVoteButton(R.drawable.ic_voute_down, { topic?.onDownVote() })

    init {
        orientation = HORIZONTAL
        addView(voteUpButton)
        addView(voteDownButton)
    }

    private fun createVoteButton(iconResId: Int, onClick: () -> Unit) = ImageView(context).apply {
        setImageResource(iconResId)
        scaleType = ImageView.ScaleType.CENTER
        setOnClickListener { onClick.invoke() }
        layoutParams = LinearLayout.LayoutParams(UiUtils.dpToPx(40f, context).toInt(), UiUtils.dpToPx(32f, context).toInt())
    }

}