package woowacourse.paint

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = "레아"
        val text = findViewById<TextView>(R.id.text)
        text.text = "$name 안녕하세요!"
    }
}
