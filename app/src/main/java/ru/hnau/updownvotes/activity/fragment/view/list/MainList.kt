package ru.hnau.updownvotes.activity.fragment.view.list

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.DividerItemDecoration

/**
 *
 * Список тем
 *
 */

class MainList(context: Context) : RecyclerView(context) {

    private val topicsAdapter = MainListAdapter(context)

    init {
        adapter = topicsAdapter

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        this.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        addItemDecoration(dividerItemDecoration)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        topicsAdapter.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        topicsAdapter.onDetachedFromWindow()
    }

}