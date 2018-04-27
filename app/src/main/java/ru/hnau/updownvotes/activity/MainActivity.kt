package ru.hnau.updownvotes.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import ru.hnau.updownvotes.R
import ru.hnau.updownvotes.activity.fragment.main_list.MainListFragment
import ru.hnau.updownvotes.activity.fragment.topic_view.TopicViewFragment

class MainActivity : AppCompatActivity() {

    private val CONTENT_ID = android.R.id.content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<View>(CONTENT_ID)?.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_dark))

        if (savedInstanceState == null) {
            addFragment(MainListFragment())
        }
    }


    fun onAddTopicButtonClicked() {

        //TODO

    }


    fun onTopicClicked(topicId: Long) = replaceFragment(TopicViewFragment.newInstance(topicId))

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(CONTENT_ID, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(CONTENT_ID, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
