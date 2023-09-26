package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rsMain.valueFrom = 0.0f
        binding.rsMain.valueTo = 100.0f

        binding.rsMain.addOnChangeListener(RangeSlider.OnChangeListener { _, value, _ ->
            binding.dpMain.setWidth(value)
        })
    }
}

