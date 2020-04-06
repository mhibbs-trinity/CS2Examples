package cs2.graphics

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color

object CircleGrid extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title = "Circles!"
        scene = new Scene(800,800) {
            val canvas = new Canvas(width.value,height.value)
            content = canvas

            val g = canvas.graphicsContext2D

            g.fill = Color.White
            g.fillRect(0,0, 800,800)
            for(x <- 0 until 800 by 50) {
                for(y <- 0 until 800 by 50) {
                    g.fill = Color.rgb(x*255/800, y*255/800, 0)
                    g.fillOval(x,y, 50,50)
                }
            }
        }
    }
}