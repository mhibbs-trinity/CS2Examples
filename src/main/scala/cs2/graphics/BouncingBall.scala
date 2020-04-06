package cs2.graphics

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.animation.AnimationTimer

object BouncingBall extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title = "Bouncing!"
        scene = new Scene(800,800) {
            val canvas = new Canvas(width.value,height.value)
            content = canvas

            val g = canvas.graphicsContext2D

            var posx = 400.0
            var posy = 400.0
            var dx= math.random * 10 - 5
            var dy= math.random * 10 - 5
            val timer = AnimationTimer(t => {
                g.fill = Color.White
                g.fillRect(0,0, 800,800)
                if(posx+50 > 800 || posx-50 < 0) dx *= -1
                if(posy+50 > 800 || posy-50 < 0) dy *= -1
                posx += dx
                posy += dy
                g.fill = Color.Red
                g.fillOval(posx-50,posy-50, 100,100)
            })
            timer.start
            
        }
    }
}