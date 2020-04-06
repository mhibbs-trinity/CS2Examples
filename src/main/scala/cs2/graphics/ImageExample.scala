package cs2.graphics

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image

object ImageExample extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title = "Circles!"
        scene = new Scene(800,800) {
            val canvas = new Canvas(width.value,height.value)
            content = canvas

            val g = canvas.graphicsContext2D

            val path = getClass.getResource("/images/player.png")
            val img = new Image(path.toString)

            g.drawImage(img, 100, 200)
        }
    }
}