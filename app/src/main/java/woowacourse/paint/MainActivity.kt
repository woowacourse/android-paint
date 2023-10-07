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
        setupFigureButtonClickListener()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun setupColorsView() {
        with(binding) {
            rvColors.adapter = ColorsAdapter(
                colors = Color.values().map { getColor(it.resId) },
                onColorClicked = pv::setBrushColor
            )
            rvColors.setHasFixedSize(true)
        }
    }

    private fun setupFigureButtonClickListener() {
        with(binding) {
            btnSquare.setOnClickListener {
                pv.setFigureToRectangle()
            }
            btnPen.setOnClickListener {
                pv.setFigureToLine()
            }
            btnCircle.setOnClickListener {
                pv.setFigureToCircle()
            }
            btnEraser.setOnClickListener {
                pv.setFigureToEraser()
            }
        }
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
