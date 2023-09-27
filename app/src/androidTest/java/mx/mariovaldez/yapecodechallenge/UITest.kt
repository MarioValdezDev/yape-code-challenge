package mx.mariovaldez.yapecodechallenge

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import mx.mariovaldez.yapecodechallenge.actions.ClickItem
import mx.mariovaldez.yapecodechallenge.actions.ScrollToBottom
import mx.mariovaldez.yapecodechallenge.home.presentation.HomeActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class UITest {

    private lateinit var scenario_home: ActivityScenario<HomeActivity>
    private lateinit var context: Context

    @Test
    fun fullTest() {
        scenario_home = ActivityScenario.launch(HomeActivity::class.java)
        context = InstrumentationRegistry.getInstrumentation().targetContext

        Thread.sleep(1000)
        results()
    }

    private fun results() {
        var accumulate = 0
        Thread.sleep(2000)
        for (k in 0 until 2) {
            for (i in 0 until 5) {
                onView(withId(R.id.recipes_recycler_view)).perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        accumulate + 1,
                        ClickItem(R.id.recipe_container)
                    )
                )
                onDetail()
                accumulate++
            }
            onView(withId(R.id.recipes_recycler_view)).perform(ScrollToBottom())
        }
    }

    private fun onDetail() {
        onView(withId(R.id.show_map_button)).perform(click())
        pressBack()
        onView(withId(R.id.details_nested_scroll_view)).perform(ViewActions.swipeUp())
        pressBack()
    }
}
