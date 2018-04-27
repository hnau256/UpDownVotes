package ru.hnau.updownvotes.activity.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.hnau.updownvotes.activity.MainActivity


abstract class MainFragment : Fragment() {

    val mainActivity: MainActivity?
        get() = activity as? MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            createView(inflater.context)

    protected abstract fun createView(context: Context): View?

}