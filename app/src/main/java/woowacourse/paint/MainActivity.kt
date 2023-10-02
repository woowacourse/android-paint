package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupColorsView()
        setupSlider()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun setupColorsView() {
        binding.rvColors.adapter = ColorsAdapter(
            colors = listOf(
                getColor(R.color.red),
                getColor(R.color.orange),
                getColor(R.color.yellow),
                getColor(R.color.green),
                getColor(R.color.blue),
            ),
            onColorClicked = binding.pv::setBrushColor
        )
        binding.rvColors.setHasFixedSize(true)
    }

    private fun setupSlider() {
        with(binding) {
            rs.addOnChangeListener(
                RangeSlider.OnChangeListener { _, value, _ ->
                    pv.setBrushWidth(value)
                }
            )
            pv.setBrushWidth(rs.valueFrom)
        }
    }
}
