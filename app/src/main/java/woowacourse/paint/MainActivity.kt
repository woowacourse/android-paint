package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.painting.figure.FigureType

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
            btnRectangle.setOnClickListener {
                pv.setFigureType(FigureType.RECTANGLE)
            }
            btnPen.setOnClickListener {
                pv.setFigureType(FigureType.LINE)
            }
            btnCircle.setOnClickListener {
                pv.setFigureType(FigureType.CIRCLE)
            }
            btnEraser.setOnClickListener {
                pv.setFigureType(FigureType.ERASER)
            }
            btnUndo.setOnClickListener {
                pv.undo()
            }
            btnRedo.setOnClickListener {
                pv.redo()
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
