package woowacourse.paint

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider


class MainActivity : AppCompatActivity() {
    private val drawingBoard: DrawingBoardView by lazy {
        findViewById(R.id.drawing_board)
    }

    private val rangeSlider: RangeSlider by lazy {
        findViewById(R.id.range_slider)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: rangeSlider.values: ${rangeSlider.values}")

        rangeSlider.addOnChangeListener { slider, value, fromUser ->
            Log.d(TAG, "rangeSlider.addOnChangeListener: value: $value, fromUser: $fromUser")
            drawingBoard.brushThickness = value
        }
    }
}

private const val TAG = "MainActivity"