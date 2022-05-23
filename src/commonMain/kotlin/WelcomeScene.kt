import com.soywiz.klock.seconds
import com.soywiz.korge.input.onClick
import com.soywiz.korge.input.onOut
import com.soywiz.korge.input.onOver
import com.soywiz.korge.scene.MaskTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korge.view.filter.TransitionFilter
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class WelcomeScene : Scene() {
    override suspend fun Container.sceneInit() {
        image(resourcesVfs["W_bg.jpg"].readBitmap()) {
            scaledWidth = 512.0
            scaledHeight = 512.0
            position(0, 0)
        }
        val startBtn = roundRect(190.0, 55.0, 12.0, 12.0, RGBA(217, 40, 24)) { // Color: D92818
            position(170, 85)
            alpha = 0.5
            onOver { alpha = 1.0 }
            onOut { alpha = 0.7 }
            onClick {
                sceneContainer.changeTo<CardsScene>(
                        transition = MaskTransition(TransitionFilter.Transition.VERTICAL),
                        time = 0.1.seconds
                )
            }
        }
        text("START", 20.0, Colors.WHITE) {
//            alignTopToTopOf(start_btn, 7)
//            alignLeftToLeftOf(start_btn, 10)
            centerOn(startBtn)
//            setTextBounds(Rectangle(178, 92, 170, 41))
            onClick{
                sceneContainer.changeTo<CardsScene>(
                        transition = MaskTransition(TransitionFilter.Transition.VERTICAL),
                        time = 0.1.seconds
                )
            }
        }
//        val start_txt = text("START", 20.0, Colors.WHITE)
//            .alignLeftToLeftOf(start_btn, 10).alignTopToTopOf(start_btn, 7)
//            .setTextBounds(Rectangle(178, 92, 170, 41))
    }
}
