package woowacourse.paint

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.view.SliderView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<SliderView>(R.id.my_slider).setOnPositionChangeListener {
            Log.d("Position", "Position : $it")
        }
    }
}