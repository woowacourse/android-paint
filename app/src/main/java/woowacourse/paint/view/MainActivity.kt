package woowacourse.paint.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.domain.BrushColor
import woowacourse.paint.domain.BrushWidth
import woowacourse.paint.view.model.mapper.LineMapper.toLine
import woowacourse.paint.view.model.mapper.LinesMapper.toModel
import woowacourse.paint.view.palette.PaletteAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initPaintView()
        initColorButtons()
        initWidthRangeSlider()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    private fun initPaintView() {
        binding.color = Color.RED
        binding.width = 3F
        binding.paintViewModel = PaintViewModel()
        binding.mainPaintView.setOnAddLine { path, paint ->
            binding.paintViewModel?.addLine(toLine(path, paint))
        }
        binding.paintViewModel?.lines?.observe(this) {
            binding.mainPaintView.setRichPaths(it.toModel())
        }
    }

    private fun initColorButtons() {
        binding.mainPalettes.adapter = PaletteAdapter(BrushColor.paintColors) {
            binding.color = it
        }
    }

    private fun initWidthRangeSlider() {
        binding.mainWidthRangeSlider.valueFrom = BrushWidth.range.start
        binding.mainWidthRangeSlider.valueTo = BrushWidth.range.endInclusive
        binding.mainWidthRangeSlider.addOnChangeListener { _, value, _ ->
            binding.width = value
        }
    }
}
