
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.DefaultTtfFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point


public val cPoint = Point(143, 89)
public val economy = Economy()

public fun easyWrap(text: String): String{
    return wrapLines(text, 14.0, 220.0, DefaultTtfFont)
}
enum class BgsColor(val color: RGBA){
    SUSPECTIONBG(RGBA(170, 56, 56)),
    CONNECTIONSBG(RGBA(174,179,171)),
    MONEYBG(RGBA(173,164,114)),
    SANITYBG(RGBA(138,200,209)),
}
class CardsScene : Scene() {
    var txtToRead = arrayListOf<String>("обро пожаловать, твой путь лежит на новую миссию")
    var vfsToRead = arrayListOf<VfsFile>(resourcesVfs["c2.png"])
    override suspend fun Container.sceneInit() {

        var deck = arrayListOf<Card>()
        for (i in 0 until vfsToRead.size) {
            deck.add(Card(txtToRead[i], vfsToRead[i].readBitmap()))
        }
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

        fun SolidRect.changeSuspection(num: Double) {
            this.removeFromParent()
            suspectionBg = solidRect(68.0, num, BgsColor.SUSPECTIONBG.color).alignLeftToLeftOf(cardBg)
                    .alignBottomToTopOf(cardBg).addTo(_backgroundLayer)
        }

        fun SolidRect.changeConnections(num: Double) {
            this.removeFromParent()
            connectionsBg = solidRect(68.0, num, BgsColor.CONNECTIONSBG.color).alignLeftToRightOf(suspectionBg)
                    .alignBottomToTopOf(cardBg).addTo(_backgroundLayer)
        }

        fun SolidRect.changeMoney(num: Double) {
            this.removeFromParent()
            moneyBg = solidRect(68.0, num, BgsColor.MONEYBG.color).alignLeftToRightOf(connectionsBg)
                    .alignBottomToTopOf(cardBg).addTo(_backgroundLayer)
        }

        fun SolidRect.changeSanity(num: Double) {
            this.removeFromParent()
            sanityBg = solidRect(68.0, num, BgsColor.SANITYBG.color).alignLeftToRightOf(moneyBg)
                    .alignBottomToTopOf(cardBg).addTo(_backgroundLayer)
        }
        image(resourcesVfs["cultist.png"].readBitmap()) {
            scaledWidth = 68.0
            scaledHeight = 68.0
        }.addTo(_midLayer)
                .alignTopToTopOf(suspectionBg).alignLeftToLeftOf(suspectionBg)
        image(resourcesVfs["discussion.png"].readBitmap()) {
            scaledWidth = 68.0
            scaledHeight = 68.0
        }.addTo(_midLayer)
                .alignTopToTopOf(connectionsBg, ).alignLeftToLeftOf(connectionsBg)
        image(resourcesVfs["cash.png"].readBitmap()) {
            scaledWidth = 68.0
            scaledHeight = 68.0
        }.addTo(_midLayer)
                .alignTopToTopOf(moneyBg, ).alignLeftToLeftOf(moneyBg, )
        image(resourcesVfs["meditation.png"].readBitmap()) {
            scaledWidth = 68.0
            scaledHeight = 68.0
        }.alignTopToTopOf(sanityBg, ).alignLeftToLeftOf(sanityBg).addTo(_midLayer)
        val n = resourcesVfs["c2.png"].readBitmap()
//        card(easyWrap("Начните приключение в новом мире"), resourcesVfs["c1.png"].readBitmap()) {
////            var i = image(this.imgLink.readBitmap()) { scaledWidth = 202.0; scaledHeight = 181.0 }.alignLeftToLeftOf(this@card, 9)
////                    .alignTopToTopOf(this@card, 28).addTo(this@card)
//            position(cPoint)
//            draggableAsCard(cPoint)
//            onCardDrag {
//                if (it.end) {
//                    when (it.throwState) {
//                        ThrowState.RIGHT -> {
//                            economy.suspection += suspectionEffect
//                            economy.sanity += sanityEffect
//                            economy.connections += connectionsEffect
//                            economy.money += moneyEffect
////                            i.removeFromParent()
//                            image(n) { scaledWidth = 202.0; scaledHeight = 181.0 }.alignLeftToLeftOf(this@card, 9)
//                                    .alignTopToTopOf(this@card, 28).addTo(this@card)
//                        }
//                        ThrowState.LEFT -> {
//                            economy.suspection -= suspectionEffect
//                            economy.sanity -= sanityEffect
//                            economy.connections -= connectionsEffect
//                            economy.money -= moneyEffect
//                        }
//                        ThrowState.CENTER -> {
////                        println("Center")
//                        }
//                    }
//                }
//            }
//        }
        card("Добро пожаловать, странник", resourcesVfs["c2.png"].readBitmap()).setDraggableRules()
        card("хмм?", resourcesVfs["c1.png"].readBitmap()).setDraggableRules()
        card("тест тест тест тест тест  тест тест  тест тест  тест тест", resourcesVfs["korge.png"].readBitmap()).setDraggableRules()
    }
}