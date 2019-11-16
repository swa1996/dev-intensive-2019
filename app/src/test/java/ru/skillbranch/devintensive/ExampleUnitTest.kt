package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.User
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testDate() {
        val date: Date = Date()
        println(date.format())
    }

    @Test
    fun testBuild() {
        val u: User = User.Builder().id("1").firstName("Михаил").lastName("Наумкин").rating(9000)
            .respect(100500).lastVisit(Date()).isOnline(true).build()
        println(u.toString())
        u.toUserView().printMe()
    }

    @Test
    fun testPlural() {
        println(TimeUnits.SECOND.plural(1))
        println(TimeUnits.SECOND.plural(2))
        println(TimeUnits.SECOND.plural(11))
        println(TimeUnits.MINUTE.plural(21))
        println(TimeUnits.HOUR.plural(21))
        println(TimeUnits.MINUTE.plural(22))
        println(TimeUnits.MINUTE.plural(30))
        println(TimeUnits.HOUR.plural(1))
        println(TimeUnits.HOUR.plural(2))
        println(TimeUnits.HOUR.plural(6))
        println(TimeUnits.HOUR.plural(10))
        println(TimeUnits.HOUR.plural(14))
        println(TimeUnits.HOUR.plural(22))
        println(TimeUnits.DAY.plural(1))
        println(TimeUnits.DAY.plural(2))
        println(TimeUnits.DAY.plural(5))
        println(TimeUnits.DAY.plural(10))
        println(TimeUnits.DAY.plural(14))
        println(TimeUnits.DAY.plural(21))
        println(TimeUnits.DAY.plural(221))
        println(TimeUnits.DAY.plural(212))
        println(TimeUnits.DAY.plural(1412))
        println(TimeUnits.DAY.plural(1422))
        println(TimeUnits.DAY.plural(2642))
    }

    @Test
    fun testHumenizeDiff() {
        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff())
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff())
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff())
        println(Date().add(7, TimeUnits.DAY).humanizeDiff())
        println(Date().add(400, TimeUnits.DAY).humanizeDiff())
        println(Date().add(400, TimeUnits.DAY))
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff())
        println(Date().add(-400, TimeUnits.DAY))
        println(Date().humanizeDiff())
    }

    @Test
    fun testStripHtml() {
        println("<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml())
        println("<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml())
    }

}