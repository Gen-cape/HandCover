
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point

public class EventManager(){
    var cardSwiped = false
}
public val cPoint = Point(143, 89)
enum class BgsColor(val color: RGBA){
    SUSPECTIONBG(RGBA(170, 56, 56)),
    CONNECTIONSBG(RGBA(174,179,171)),
    MONEYBG(RGBA(173,164,114)),
    SANITYBG(RGBA(138,200,209)),
}
class CardsScene : Scene() {
    override suspend fun Container.sceneInit() {
         var events = EventManager()
        var money: Int = 100
        var suspection: Int = 100
        var sanity: Int = 100
        var connections : Int = 100
         var left : String = "Нет"
         var right : String = "Да"
         var curr = 0
        solidRect(512, 512, RGBA(196, 196, 196)).xy(0, 0) // Color: c4c4c4
        solidRect(512, 62, RGBA(89, 59, 2)).xy(0, 450).alignBottomToBottomOf(sceneContainer) // Color: 593B02
        val _backgroundLayer = container {}
        val _midLayer = container {}
        val cardBg =
                solidRect(272, 356, RGBA(140, 91, 73)).xy(117, 68).addTo(_backgroundLayer) //8b5c49
        var suspectionBg = solidRect(68, 68, BgsColor.SUSPECTIONBG.color) { // Color: AA3838
            alignLeftToLeftOf(cardBg)
            alignBottomToTopOf(cardBg)
        }.addTo(_backgroundLayer)
        var connectionsBg = solidRect(68, 68, BgsColor.CONNECTIONSBG.color) { // Color:885C5C
            alignLeftToRightOf(suspectionBg)
            alignBottomToTopOf(cardBg)
        }.addTo(_backgroundLayer)
        var moneyBg = solidRect(68, 68, BgsColor.MONEYBG.color) { // Color: 7E4747
            alignLeftToRightOf(connectionsBg)
            alignBottomToTopOf(cardBg)
        }.addTo(_backgroundLayer)
        var sanityBg = solidRect(68, 68, BgsColor.SANITYBG.color) { // Color: 573434
            alignLeftToRightOf(moneyBg)
            alignBottomToTopOf(cardBg)
        }.addTo(_backgroundLayer)

        var choiceOne = text(choiceWrapper(left), color = Colors.WHITE) { position(11, 469) }
        var choiceTwo = text(choiceWrapper(right), color = Colors.WHITE) { position(363, 469) }

        fun SolidRect.changeSuspection(num: Int) {
            this.removeFromParent()
            suspectionBg = solidRect(68, num, BgsColor.SUSPECTIONBG.color).alignLeftToLeftOf(cardBg)
                    .alignBottomToTopOf(cardBg).addTo(_backgroundLayer)
        }

        fun SolidRect.changeConnections(num: Int) {
            this.removeFromParent()
            connectionsBg = solidRect(68, num, BgsColor.CONNECTIONSBG.color).alignLeftToRightOf(suspectionBg)
                    .alignBottomToTopOf(cardBg).addTo(_backgroundLayer)
        }

        fun SolidRect.changeMoney(num: Int) {
            this.removeFromParent()
            moneyBg = solidRect(68, num, BgsColor.MONEYBG.color).alignLeftToRightOf(connectionsBg)
                    .alignBottomToTopOf(cardBg).addTo(_backgroundLayer)
        }

        fun SolidRect.changeSanity(num: Int) {
            this.removeFromParent()
            sanityBg = solidRect(68, num, BgsColor.SANITYBG.color).alignLeftToRightOf(moneyBg)
                    .alignBottomToTopOf(cardBg).addTo(_backgroundLayer)
        }

//        fun Text.updateChoiceOne() {
//            var choiceOne = text(choiceWrapper("TEST"), color = Colors.RED) { position(11, 469) }
//            this.removeFromParent()
//        }
//
//        fun Text.updateChoiceTwo() {
//            this.removeFromParent()
//            var choiceTwo = text(choiceWrapper(right), color = Colors.RED) { position(363, 469) }
//        }
        image(resourcesVfs["cultist.png"].readBitmap()) {
            scaledWidth = 68.0
            scaledHeight = 68.0
        }.addTo(_midLayer)
                .alignTopToTopOf(suspectionBg).alignLeftToLeftOf(suspectionBg)
        image(resourcesVfs["discussion.png"].readBitmap()) {
            scaledWidth = 68.0
            scaledHeight = 68.0
        }.addTo(_midLayer)
                .alignTopToTopOf(connectionsBg).alignLeftToLeftOf(connectionsBg)
        image(resourcesVfs["cash.png"].readBitmap()) {
            scaledWidth = 68.0
            scaledHeight = 68.0
        }.addTo(_midLayer)
                .alignTopToTopOf(moneyBg).alignLeftToLeftOf(moneyBg)
        image(resourcesVfs["meditation.png"].readBitmap()) {
            scaledWidth = 68.0
            scaledHeight = 68.0
        }.alignTopToTopOf(sanityBg, ).alignLeftToLeftOf(sanityBg).addTo(_midLayer)
        fun Card.setDraggableRules(choices1:String, choices2 : String, suspectionEffect: Int,
                                   moneyEffect: Int, connectionsEffect: Int, sanityEffect: Int) {
            val choice = choices1
            val card = this
            onCardDrag {
                if (it.end) {
                    when (it.throwState) {
                        ThrowState.RIGHT -> {
//                            left = choice
//                            right = choices2
//                            choiceOne.updateChoiceOne()
//                            choiceTwo.updateChoiceTwo()
                            suspectionBg.changeSuspection(suspection)
                            moneyBg.changeMoney(money)
                            connectionsBg.changeConnections(connections)
                            suspection += suspectionEffect
                            sanity += sanityEffect
                            connections += connectionsEffect
                            money += moneyEffect
                            card.pull()
                        }
                        ThrowState.LEFT -> {
//                            left = choice
//                            right = choices2
//                            choiceOne.updateChoiceOne()
//                            choiceTwo.updateChoiceTwo()
                            suspection -= suspectionEffect
                            sanity -= sanityEffect
                            connections -= connectionsEffect
                            money -= moneyEffect
                            card.pull()
                        }
                        ThrowState.CENTER -> {
//                            left = choice
//                            right = choices2
//                            choiceOne.updateChoiceOne()
//                            choiceTwo.updateChoiceTwo()
//                        println("Center")
                        }
                    }
                }
            }
        }
        card("Приветствую, перейду сразу к делу. Ты должен выкрасть документы и провести шпионаж", resourcesVfs["c1.png"].readBitmap())
                .setDraggableRules("Принято", "Выдвигаюсь", 10, 5, 2, 10);
        card("Вы заселились в новый дом, стоит ли быть приветливыми со всеми?", resourcesVfs["q2.png"].readBitmap())
                .setDraggableRules("Нет, это подозрительно", "Обязательно нужно быть приветливым", 5, 0, 10, -10);
//        card("тест тест тест тест тест  тест тест  тест тест  тест тест", resourcesVfs["korge.png"].readBitmap()).setDraggableRules()
    }
}