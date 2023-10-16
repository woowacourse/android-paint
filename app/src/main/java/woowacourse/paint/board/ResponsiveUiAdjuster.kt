package woowacourse.paint.board

import android.content.Context
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.content.res.Resources
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import kotlin.math.min
import kotlin.math.roundToInt

class ResponsiveUiAdjuster(private val context: Context) {

    private val isPhone: Boolean = min(
        getDeviceHeightDPValue(context.resources),
        getDeviceWidthDPValue(context.resources),
    ) < DEVICE_TYPE_BOUNDARY

    private val statusBarSize = getStatusBarHeight()
    private val navigateBarSize = getEnabledNavigateBarHeight()
    private val screenHeight = context.resources.displayMetrics.heightPixels
    private val screenWidth = context.resources.displayMetrics.widthPixels

    fun calculatePaletteXPosition(BoardPosition: Float) =
        when (isPhone) {
            true -> -BoardPosition
            false -> -BoardPosition
        }

    fun calculatePaletteYPosition(BoardYPosition: Float, paletteHeight: Float) =
        when (isPhone) {
            true -> -BoardYPosition + getScreenHeightExcludeStatusBarNavigationBar() - paletteHeight - PALETTE_BOTTOM_MARGIN_PIXEL
            false -> -BoardYPosition
        }

    private fun getDeviceWidthDPValue(resources: Resources): Int =
        (resources.displayMetrics.widthPixels / resources.displayMetrics.density).roundToInt()

    private fun getDeviceHeightDPValue(resources: Resources): Int =
        (resources.displayMetrics.heightPixels / resources.displayMetrics.density).roundToInt()

    private fun getScreenHeightExcludeStatusBarNavigationBar(): Float =
        screenHeight - statusBarSize - navigateBarSize

    private fun getStatusBarHeight(): Float {
        val navigationBarId =
            context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(navigationBarId).toFloat()
    }

    private fun getEnabledNavigateBarHeight(): Float {
        val navigationBarId =
            context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(navigationBarId).toFloat()
    }

    fun calculatePaletteWidth(): Int =
        when (context.resources.configuration.orientation) {
            ORIENTATION_PORTRAIT -> MATCH_PARENT
            ORIENTATION_LANDSCAPE -> screenWidth / 2
            else -> MATCH_PARENT
        }

    companion object {
        private const val DEVICE_TYPE_BOUNDARY = 600
        private const val PALETTE_BOTTOM_MARGIN_PIXEL = 20
    }
}
