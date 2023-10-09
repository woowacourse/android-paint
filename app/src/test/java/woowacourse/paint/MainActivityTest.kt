package woowacourse.paint

import android.os.SystemClock
import android.view.MotionEvent
import androidx.lifecycle.ViewModelProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import woowacourse.paint.view.MainActivity
import woowacourse.paint.view.PaintView
import woowacourse.paint.view.PaintViewModel

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `PaintView를 드래그하면 PaintViewModel의 lines LiveData에 Inks 객체가 추가된다`() {
        activityScenarioRule.scenario.onActivity { activity ->
            // given
            val paintViewModel = ViewModelProvider(activity)[PaintViewModel::class.java]
            val paintView = activity.findViewById<PaintView>(R.id.main_paint_view)

            // when
            val downTime = SystemClock.uptimeMillis()
            paintView.dispatchTouchEvent(
                MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, 10F, 10F, 0)
            )
            paintView.dispatchTouchEvent(
                MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_MOVE, 15F, 10F, 0)
            )
            paintView.dispatchTouchEvent(
                MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_UP, 10F, 10F, 0)
            )

            // then
            val expected = 1
            val actual = paintViewModel.lines.getOrAwaitValue().data.size
            assertEquals(expected, actual)
        }
    }
}
