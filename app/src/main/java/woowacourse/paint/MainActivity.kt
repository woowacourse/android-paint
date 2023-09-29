package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.palette.PaletteAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private var thickness = 0f
    private var color = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setListener()
        initRecyclerView()
        viewBinding.rangebar.valueFrom = 0.0f
        viewBinding.rangebar.valueTo = 100.0f
    }

    private fun initRecyclerView() {
        val colors = listOf(
            getColor(R.color.red),
            getColor(R.color.orange),
            getColor(R.color.yellow),
            getColor(R.color.green),
            getColor(R.color.blue),
        )
        val adapter = PaletteAdapter(colors) { color = colors[it] }
        viewBinding.rvColor.adapter = adapter
    }

    private fun setListener() {
        viewBinding.btnColorChange.setOnClickListener {
            viewBinding.customCanvas.changeColor(color)
        }

        viewBinding.btnThicknessChange.setOnClickListener {
            viewBinding.customCanvas.changeThickness(thickness)
        }

        viewBinding.rangebar.addOnChangeListener { _, value, _ ->
            thickness = value
        }
    }
}
