import com.soywiz.korim.font.Font
import com.soywiz.korim.font.TextMetrics
import com.soywiz.korim.font.getTextBounds

class Utils {
    fun wrapLines(text: String, textSize: Double, width: Double, font: Font): String {
        val words = text.split(' ')

        val out = StringBuilder()


        var line = ""
        val metrics = TextMetrics()
        for (word in words) {
            val testLine = if (line.isEmpty()) {
                word
            } else {
                "$line $word"
            }

            font.getTextBounds(textSize, testLine, metrics)

            line = if (metrics.bounds.width > width) {
                out.append(line)
                out.append('\n')
                word
            } else {
                testLine
            }
        }

        out.append(line)

        return out.toString()
    }
}