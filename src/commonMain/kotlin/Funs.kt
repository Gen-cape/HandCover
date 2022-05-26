
import com.soywiz.korim.font.DefaultTtfFont
import com.soywiz.korim.font.Font
import com.soywiz.korim.font.TextMetrics
import com.soywiz.korim.font.getTextBounds



public fun choiceWrapper(text: String): String{
    return wrapLines(text, 20.0, 132.0, DefaultTtfFont)
}
public fun easyWrap(text: String): String{
    return wrapLines(text, 14.0, 220.0, DefaultTtfFont)
}
public fun wrapLines(text: String, textSize: Double, width: Double, font: Font): String {
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


class Funs {
    // Keeping class for possible functions with anchor
}