package ru.hnau.updownvotes.data

/**
 *
 * Данные о рейтинге новости
 *
 */

data class TopicRatingInfo(
        val upVotes: Int = 0,
        val downVotes: Int = 0
) {

    val rating = upVotes - downVotes

    fun onUpVote() = copy(upVotes = upVotes + 1)

    fun onDownVote() = copy(downVotes = downVotes + 1)

}