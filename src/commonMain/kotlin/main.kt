
import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import kotlin.reflect.KClass

suspend fun main() = Korge(Korge.Config(module = MainModule))

object MainModule : Module() {
	//	override val mainScene: KClass<out Scene> = WelcomeScene::class
	override val mainScene: KClass<out Scene> = WelcomeScene::class
	override val bgcolor: RGBA
		get() = Colors.BLACK
	override val size: SizeInt
		get() = SizeInt(512, 512)
//	override val icon: String
//		get() = "src/commonMain/resources/icon.png"
	//        get() = super.icon
	override val title: String
		get() = "HandCover"
	override suspend fun AsyncInjector.configure() {
		mapPrototype { WelcomeScene() }
		mapPrototype { CardsScene() }
//		mapPrototype{ GameOver()}
	}
}