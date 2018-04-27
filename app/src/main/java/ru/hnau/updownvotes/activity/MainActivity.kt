package ru.hnau.updownvotes.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import ru.hnau.updownvotes.activity.fragment.add_topic.AddTopicFragment
import ru.hnau.updownvotes.activity.fragment.main_list.MainListFragment
import ru.hnau.updownvotes.activity.fragment.topic_view.TopicViewFragment

class MainActivity : AppCompatActivity() {

    //Идентификатор корневого View
    private val CONTENT_ID = android.R.id.content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            addFragment(MainListFragment())
        }
    }


    fun onAddTopicButtonClicked() = replaceFragment(AddTopicFragment())

    fun goBack() = supportFragmentManager.popBackStack()

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
