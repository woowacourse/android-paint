package woowacourse.paint

import android.os.Parcelable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Parcelize
class Picasso(private val picassoPaths: List<PicassoPath>) : Iterable<PicassoPath>, Parcelable {
    override fun iterator(): Iterator<PicassoPath> = picassoPaths.iterator()
    operator fun get(index: Int) = picassoPaths[index]
    fun add(picassoPath: PicassoPath): Picasso = Picasso(picassoPaths.plusElement(picassoPath))
}

@Parcelize
class PicassoPath(val color: PicassoColor, val width: Float, private val offsets: List<PicassoOffset>) : Iterable<PicassoOffset>, Parcelable {
    override fun iterator(): Iterator<PicassoOffset> = offsets.iterator()
    operator fun get(index: Int) = offsets[index]
    fun add(offset: PicassoOffset): PicassoPath = PicassoPath(color, width, offsets + offset)
}

@Parcelize
data class PicassoOffset(val x: Float, val y: Float) : Parcelable {
    companion object {
        fun of(offset: Offset) = PicassoOffset(offset.x, offset.y)
    }
}

enum class PicassoColor {
    RED, ORANGE, YELLOW, GREEN, BLUE;
}


fun PicassoColor.toColor(): Color {
    return when(this) {
        PicassoColor.RED -> Color.Red
        PicassoColor.ORANGE -> Color(0xFFFFA500)
        PicassoColor.YELLOW -> Color.Yellow
        PicassoColor.GREEN -> Color.Green
        PicassoColor.BLUE -> Color.Blue
    }
}
