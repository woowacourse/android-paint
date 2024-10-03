package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val colorMap =
        mapOf(
            R.id.redColor to R.color.red,
            R.id.orangeColor to R.color.orange,
            R.id.yellowColor to R.color.yellow,
            R.id.greenColor to R.color.green,
            R.id.blueColor to R.color.blue,
        )

    private val toolsMap =
        mapOf(
            R.id.pen_tool to ShapeType.FREEHAND,
            R.id.rectangle_tool to ShapeType.RECTANGLE,
            R.id.circle_tool to ShapeType.CIRCLE,
            R.id.eraser_tool to ShapeType.ERASER,
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeRangeSlider()
        initializeColorChips()
        initializeToolChips()
    }

    private fun initializeRangeSlider() {
        binding.rangeSlider.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                binding.paintBoard.setStrokeWidth(value)
            }
        }
    }

    private fun initializeColorChips() {
        colorMap.forEach { (chipId, colorId) ->
            findViewById<View>(chipId).setOnClickListener {
                binding.paintBoard.setColor(colorId)
            }
        }
    }

    private fun initializeToolChips() {
        toolsMap.forEach { (chipId, shapeType) ->
            findViewById<View>(chipId).setOnClickListener {
                binding.paintBoard.setShapeType(shapeType)
            }
        }
    }
}
