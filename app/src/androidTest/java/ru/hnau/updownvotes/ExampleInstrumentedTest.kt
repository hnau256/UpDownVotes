package ru.hnau.updownvotes

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import ru.hnau.updownvotes.data.Topic
import ru.hnau.updownvotes.data.TopicsManager

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("ru.hnau.updownvotes", appContext.packageName)
    }

    // Проверка TopicsManager'а на добавление темы
    @Test
    fun topicsManagerAddTopicTest() {

        TopicsManager.clear()
        val text = "Test topic text"
        val topicId = TopicsManager.addTopic(text)
        val topicById = TopicsManager.getTopicById(topicId)

        assertEquals(text, topicById?.text)

    }

    // Проверка TopicsManager'а на корректность вещания тем
    @Test
    fun topicsManagerProducerTest() {

        TopicsManager.clear()
        val text = "Test topic text"
        TopicsManager.addTopic(text)

        TopicsManager.attach {
            // Текст единственной (если больше одной, то null) темы
            val singleTopicText = it.takeIf { it.size == 1 }?.firstOrNull()?.let { TopicsManager.getTopicById(it) }?.text
            assertEquals(text, singleTopicText)
        }.detach()

    }

    // Проверка подсчета голосов темы
    @Test
    fun topicRatingTest() {

        val topic = Topic(0, "")

        topic.onDownVote()
        topic.onUpVote()
        topic.onUpVote()
        topic.onDownVote()
        topic.onDownVote()
        topic.onUpVote()
        topic.onDownVote()

        //Сейчас у темы рейтинг -1 (плюсов - 3, минусов - 4)

        topic.attach {
            assertEquals(-1, it.rating)
            assertEquals(3, it.upVotes)
            assertEquals(4, it.downVotes)
        }.detach()

    }

    // Проверка проверки текста темы
    @Test
    fun topicCheckTextTest() {

        val context = InstrumentationRegistry.getTargetContext()

        val errorTextEmpty = Topic.checkText(context, "").error
        assertEquals("Текст темы не может быть пустым", errorTextEmpty)

        val errorTextTooLong = Topic.checkText(context, (0 until 1024).joinToString("", transform = { "a" })).error
        assertEquals("Длина текста темы не может превышать 255 символов", errorTextTooLong)

    }

}
