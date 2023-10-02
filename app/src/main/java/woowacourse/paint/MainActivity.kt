package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPenTool()
    }

    private fun initPenTool() {
        val selectedPen = binding.penToolView.selectedPen

        binding.btnOpenPenTool.setOnClickListener {
            if (binding.penToolView.visibility == View.VISIBLE) {
                binding.penToolView.visibility = View.GONE
            } else if (binding.penToolView.visibility == View.GONE) {
                binding.penToolView.visibility = View.VISIBLE
            }
        }

        binding.penToolView.setPenChangeClickEvent {
            binding.penToolView.visibility = View.GONE
            binding.paintView.pen = selectedPen
        }
    }
}
