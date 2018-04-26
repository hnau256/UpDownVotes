package ru.hnau.updownvotes.data

import android.content.Context
import ru.hnau.updownvotes.R
import ru.hnau.updownvotes.producer.CachedProducer
import ru.hnau.updownvotes.utils.CheckResult

/**
 *
 * Тема
 *
 */

class Topic(
        val text: String,
        upCount: Int = 0,
        downCount: Int = 0
) : CachedProducer<Int>(0) {

    companion object {

        private const val TEXT_MAX_LENGTH = 255

        fun checkText(context: Context, text: String): CheckResult {
            if (text.isEmpty()) {
                val reason = context.getString(R.string.topic_check_error_empty)
                return CheckResult.incorrect(reason)
            }
            if (text.length > TEXT_MAX_LENGTH) {
                val reason = context.getString(R.string.topic_check_error_too_long, TEXT_MAX_LENGTH)
                return CheckResult.incorrect(reason)
            }
            return CheckResult.correct()
        }

    }

    var upCount = upCount
        private set(value) {
            if (field != value) {
                field = value
                onRatingChanged()
            }
        }

    var downCount = downCount
        private set(value) {
            if (field != value) {
                field = value
                onRatingChanged()
            }
        }

    val rating: Int
        get() = upCount - downCount

    fun onDownVote() {
        downCount++
    }

    fun onUpVote() {
        upCount++
    }

    private fun onRatingChanged() {
        cachedData = rating
    }

}