package ru.hnau.updownvotes.activity.fragment.view.list

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


class MainList(context: Context) : RecyclerView(context) {

    private val topicsAdapter = MainListAdapter(context)

    init {
        adapter = topicsAdapter
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
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