package woowacourse.paint

import android.os.Bundle
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
        binding.penToolView.setChangeColorClickEvent {
            binding.paintView.pen = selectedPen
        }
        binding.penToolView.setChangeWidthClickEvent {
            binding.paintView.pen = selectedPen
        }
    }
}
