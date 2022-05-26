
import com.soywiz.klock.seconds
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.MaskTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korge.view.filter.TransitionFilter
import com.soywiz.korim.color.Colors

class GameOver() : Scene() {
    override suspend fun Container.sceneInit() {
        val s = solidRect(512.0, 512.0, Colors.BLACK)
        val txt = text("Вы проиграли").centerOn(s)
        text("Попробовать еще раз?").centerXOn(txt).alignTopToTopOf(txt, 10)
        onClick{
            sceneContainer.changeTo<WelcomeScene>(
                    transition = MaskTransition(TransitionFilter.Transition.VERTICAL),
                    time = 0.1.seconds
            )
        }
    }
}
