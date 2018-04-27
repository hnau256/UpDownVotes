package ru.hnau.updownvotes.utils


/**
 * Результат проверки чего либо
 */

class CheckResult private constructor(
        private val error: String?
) {

    companion object {

        fun correct() = CheckResult(null)

        fun incorrect(reason: String) = CheckResult(reason)

    }

    fun handle(
            onCorrect: () -> Unit,
            onIncorrect: (resson: String) -> Unit
    ) {
        if (error == null) {
            onCorrect.invoke()
            return
        }
        onIncorrect.invoke(error)
    }

}