package ru.hnau.updownvotes.utils


/**
 * Результат проверки чего либо
 */

class CheckResult private constructor(
        val error: String?
) {

    companion object {

        fun correct() = CheckResult(null)

        fun incorrect(reason: String) = CheckResult(reason)

    }

}