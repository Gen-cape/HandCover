
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.DefaultTtfFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point

public fun easyWrap(text: String): String{
    return wrapLines(text, 14.0, 220.0, DefaultTtfFont)
}
class CardsScene : Scene() {
    var txtToRead = arrayListOf<String>("обро пожаловать, твой путь лежит на новую миссию")
    var vfsToRead = arrayListOf<VfsFile>(resourcesVfs["c2.png"])
    override suspend fun Container.sceneInit() {
        var deck = arrayListOf<Card>()
        for (i in 0 until vfsToRead.size){
            deck.add(Card(txtToRead[i], vfsToRead[i]))
        }
        val economy = Economy()
        solidRect(512, 512, RGBA(196, 196, 196)).xy(0, 0) // Color: c4c4c4
        solidRect(512, 62, RGBA(89, 59, 2)).xy(0, 450).alignBottomToBottomOf(sceneContainer) // Color: 593B02
        val _backgroundLayer = container{}
        val cardBg =
                solidRect(272, 356, RGBA(140, 91, 73)).xy(117, 68).addTo(_backgroundLayer) //8b5c49
        val suspectionBg = solidRect(68, 68, RGBA(170, 56, 56)) { // Color: AA3838
            alignLeftToLeftOf(cardBg)
            alignBottomToTopOf(cardBg)
        }.addTo(_backgroundLayer)
        val connectionsBg = solidRect(68, 68, RGBA(174,179,171)) { // Color:885C5C
            alignLeftToRightOf(suspectionBg)
            alignBottomToTopOf(cardBg)
        }.addTo(_backgroundLayer)
        val moneyBg = solidRect(68, 68, RGBA(173,164,114)) { // Color: 7E4747
            alignLeftToRightOf(connectionsBg)
            alignBottomToTopOf(cardBg)
        }
        val sanityBg = solidRect(68, 68, RGBA(138,200,209)) { // Color: 573434
            alignLeftToRightOf(moneyBg)
            alignBottomToTopOf(cardBg)
        }.addTo(_backgroundLayer)
        image(resourcesVfs["cultist.png"].readBitmap()){
            scaledWidth = 68.0
            scaledHeight = 68.0
        }
                .alignTopToTopOf(suspectionBg).alignLeftToLeftOf(suspectionBg)
        image(resourcesVfs["discussion.png"].readBitmap()){
            scaledWidth = 68.0
            scaledHeight = 68.0
        }
                .alignTopToTopOf(connectionsBg, ).alignLeftToLeftOf(connectionsBg)
        image(resourcesVfs["cash.png"].readBitmap()){
            scaledWidth = 68.0
            scaledHeight = 68.0
        }
                .alignTopToTopOf(moneyBg, ).alignLeftToLeftOf(moneyBg, )
        image(resourcesVfs["meditation.png"].readBitmap()){
            scaledWidth = 68.0
            scaledHeight = 68.0
        }.alignTopToTopOf(sanityBg, ).alignLeftToLeftOf(sanityBg)

        val cPoint = Point(143, 89)
        val n = resourcesVfs["c2.png"].readBitmap()
        card(easyWrap("Начните приключение в новом мире"), resourcesVfs["c1.png"]) {
            var i = image(this.imgLink.readBitmap()){scaledWidth = 202.0; scaledHeight = 181.0}.alignLeftToLeftOf(this@card, 9)
                    .alignTopToTopOf(this@card, 28).addTo(this@card)
            position(143, 89)
            draggableAsCard(cPoint)
            onCardDrag{
                if (it.end) {
                    when (it.throwState) {
                        ThrowState.RIGHT -> {
                            economy.suspection += suspectionEffect
                            economy.sanity += sanityEffect
                            economy.connections += connectionsEffect
                            economy.money += moneyEffect
                            i.removeFromParent()
                            image(n){scaledWidth = 202.0; scaledHeight = 181.0}.alignLeftToLeftOf(this@card, 9)
                                    .alignTopToTopOf(this@card, 28).addTo(this@card)
                        }
                        ThrowState.LEFT -> {
                            economy.suspection -= suspectionEffect
                            economy.sanity -= sanityEffect
                            economy.connections -= connectionsEffect
                            economy.money -= moneyEffect
                        }
                        ThrowState.CENTER -> {
//                        println("Center")
                        }
                    }
                }
            }
        }
    }
}
