package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private var thickness = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setListener()
    }

    private fun setListener() {
        viewBinding.btnColorChange.setOnClickListener {
        }

        viewBinding.btnThicknessChange.setOnClickListener {
            viewBinding.customCanvas.changeThickness(thickness)
        }

        viewBinding.rangebar.addOnChangeListener { _, value, _ ->
            thickness = value
        }
    }
}
