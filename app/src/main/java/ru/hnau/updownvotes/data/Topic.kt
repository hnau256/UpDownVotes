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
        val text: String
) : CachedProducer<TopicRatingInfo>(
        TopicRatingInfo()
) {

    //Сортировка идет по количеству плюсов
    val sortOrder: Int
        get() = cachedData.upVotes

    fun onUpVote() {
        cachedData = cachedData.onUpVote()
    }

    fun onDownVote() {
        cachedData = cachedData.onDownVote()
    }

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

}