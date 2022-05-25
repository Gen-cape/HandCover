
import com.soywiz.korge.view.*
import com.soywiz.korge.view.tween.moveTo
import com.soywiz.korge.view.tween.rotateBy
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.Angle
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.cos
import com.soywiz.korma.geom.sin

fun Stage.specialRotate(container: Container, x: Double, y: Double, angle: Angle) = launchImmediately {
    container.rotateBy(angle)
    val centerX = container.width.div(2)
    val centerY = container.height.div(2)
    val toX = (x - centerX) * cos(angle) - (y - centerY) * sin(angle)
    val toY = (x - centerX) * sin(angle) - (y - centerY) * cos(angle)
    container.moveTo(toX, toY)
}

inline fun Container.card(
        description: String, btmp: Bitmap, callback:  Card.() -> Unit = {}
): Card = Card(easyWrap(description), btmp).addTo(this, callback).position(cPoint).draggableAsCard(cPoint)

fun Card.setDraggableRules() {
    val card = this
    onCardDrag {
        if (it.end) {
            when (it.throwState) {
                ThrowState.RIGHT -> {
                    economy.suspection += suspectionEffect
                    economy.sanity += sanityEffect
                    economy.connections += connectionsEffect
                    economy.money += moneyEffect
                    card.pull()
                }
                ThrowState.LEFT -> {
                    economy.suspection -= suspectionEffect
                    economy.sanity -= sanityEffect
                    economy.connections -= connectionsEffect
                    economy.money -= moneyEffect
                    card.pull()
                }
                ThrowState.CENTER -> {
//                        println("Center")
                }
            }
        }
    }
}
class Card : Container {
    private var description: String

    constructor(description: String, btmp: Bitmap) : super() {
        val desc: String
        this.description = description
        val bg = roundRect(220, 314, 4, fill = RGBA(242, 212, 155)) // Color: D0CDB7 // F2D49B // 8C5B49
        text(easyWrap(description), color = Colors.BLACK) {
            textSize = 14.0
            centerXOn(bg)
            centerYBetween(218, 286)
            setTextBounds(Rectangle(9, 215, 202, 58))
        }
        image(btmp){scaledWidth = 202.0; scaledHeight = 181.0 }.alignLeftToLeftOf(this, 9)
    .alignTopToTopOf(this, 28).addTo(this)
    }
    val suspectionEffect: Double = 0.0
    val moneyEffect: Double = 0.0
    val connectionsEffect: Double = 0.0
    val sanityEffect: Double = 0.0
    val pair = Pair("Df choice 1", "Df choice 2")

    fun pull() {
        this.removeFromParent()
    }
}
