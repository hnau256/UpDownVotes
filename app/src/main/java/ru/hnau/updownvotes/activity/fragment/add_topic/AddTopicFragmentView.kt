package ru.hnau.updownvotes.activity.fragment.add_topic

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.InputFilter
import android.util.TypedValue
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import ru.hnau.updownvotes.R
import ru.hnau.updownvotes.activity.view.FlatButton
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.data.TopicsManager
import ru.hnau.updownvotes.producer.Producer
import ru.hnau.updownvotes.utils.UiUtils


class AddTopicFragmentView(context: Context) : LinearLayout(context) {

    val onTopicAddedProducer = Producer<Unit>()

    private val editView = EditText(context).apply {

        layoutParams = LinearLayout.LayoutParams(UiUtils.MATCH_PARENT, 0, 1f)

        val paddingV = UiUtils.dpToPx(8f, context).toInt()
        val paddingH = UiUtils.dpToPx(16f, context).toInt()
        setPadding(paddingH, paddingV, paddingH, paddingV)


        setTextColor(ContextCompat.getColor(context, R.color.fg))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)

        filters += InputFilter.LengthFilter(Topic.TEXT_MAX_LENGTH)
        imeOptions = EditorInfo.IME_ACTION_DONE
        setBackgroundColor(Color.TRANSPARENT)
        gravity = Gravity.TOP

        hint = context.getString(R.string.add_topic_fragment_edit_hint)
        setHintTextColor(ContextCompat.getColor(context, R.color.fg_50))
    }

    private val addButton = FlatButton(
            context = context,
            titleResId = R.string.add_topic_fragment_add,
            onClick = this::onAddClicked
    ).apply {
        layoutParams = LinearLayout.LayoutParams(UiUtils.MATCH_PARENT, UiUtils.WRAP_CONTENT)
    }

    init {
        orientation = VERTICAL
        addView(editView)
        addView(addButton)

        post(this::requestFocusToEditView)
    }

    private fun requestFocusToEditView() {
        editView.requestFocusFromTouch()
        val lManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        lManager.showSoftInput(editView, 0)
    }

    private fun onAddClicked() {
        val text = editView.text.toString()
        Topic.checkText(context, text).handle(
                onCorrect = {
                    TopicsManager.addTopic(text)
                    onTopicAddedProducer.call(Unit)
                },
                onIncorrect = {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
        )
    }

}