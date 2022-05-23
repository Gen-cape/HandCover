
import com.soywiz.korge.view.*
import com.soywiz.korge.view.tween.moveTo
import com.soywiz.korge.view.tween.rotateBy
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.VfsFile
import com.soywiz.korma.geom.Angle
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.cos
import com.soywiz.korma.geom.sin

public fun Stage.specialRotate(container: Container, x: Double, y: Double, angle: Angle) = launchImmediately {
    container.rotateBy(angle)
    val centerX = container.width.div(2)
    val centerY = container.height.div(2)
    val toX = (x - centerX) * cos(angle) - (y - centerY) * sin(angle)
    val toY = (x - centerX) * sin(angle) - (y - centerY) * cos(angle)
    container.moveTo(toX, toY)
}

//fun Container.card (description: String, function: () -> Unit) = Card(description).addTo(this)
inline fun Container.card(
        description: String, imgLink: VfsFile, callback:  Card.() -> Unit = {}
): Card = Card(description, imgLink).addTo(this, callback)
class Card(
        private var description: String, img: VfsFile
) : Container() {
    public val imgLink = img
    public val desc = description
    val suspectionEffect: Double = 0.0
    val moneyEffect: Double = 0.0
    val connectionsEffect: Double = 0.0
    val sanityEffect: Double = 0.0

    fun pull() {
        this.removeFromParent()
    }
    init {
        val bg = roundRect(220, 314, 4, fill = RGBA(242, 212, 155)) // Color: D0CDB7 // F2D49B // 8C5B49
//        val temp_img = solidRect(202, 181, RGBA(93, 89,52)){ // Color: 5D5934
//            alignTopToTopOf(bg, 28)
//            alignLeftToLeftOf(bg, 9)
//        }
        text(description, color = Colors.BLACK) {
            textSize = 14.0
            centerXOn(bg)
            centerYBetween(218, 286)
            setTextBounds(Rectangle(9, 215, 202, 58))
        }
    }
}
