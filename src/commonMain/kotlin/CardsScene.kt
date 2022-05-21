import com.soywiz.korge.input.onMouseDrag
import com.soywiz.korge.input.onSwipe
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.RGBA
import com.soywiz.korma.geom.Point

class CardsScene : Scene() {
    override suspend fun Container.sceneInit() {
//        addComponent(SwipeProcessor(this))
        val bg = solidRect(512, 512, RGBA(196, 196, 196)).xy(0, 0) // Color: c4c4c4
        val border = solidRect(512, 62, RGBA(89, 59, 2)).xy(0, 450).alignBottomToBottomOf(sceneContainer) // Color: 593B02
        val cardBg =
                solidRect(272, 356, RGBA(140, 91, 73)).xy(117, 68) //8b5c49
        val suspectionBg = solidRect(68, 68, RGBA(170, 56, 56)) { // Color: AA3838
            alignLeftToLeftOf(cardBg)
            alignBottomToTopOf(cardBg)
        }
        val connectionsBg = solidRect(68, 68, RGBA(136, 92, 92)) { // Color:885C5C
            alignLeftToRightOf(suspectionBg)
            alignBottomToTopOf(cardBg)
        }
        val moneyBg = solidRect(68, 68, RGBA(126, 71, 71)) { // Color: 7E4747
            alignLeftToRightOf(connectionsBg)
            alignBottomToTopOf(cardBg)
        }
        val _bg = solidRect(68, 68, RGBA(87, 52, 52)) { // Color: 573434
            alignLeftToRightOf(moneyBg)
            alignBottomToTopOf(cardBg)
        }
        val cPoint = Point(143, 89)
        container {
            position(143, 89)
            card("Lorem ipsum inmurito inguido aster")
            draggableAsCard(cPoint)
            onCardDrag{

            }
        }
    }
}
