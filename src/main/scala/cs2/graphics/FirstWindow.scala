package cs2.graphics

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.scene.image.Image
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode

object FirstWindow extends JFXApp {
    stage = new JFXApp.PrimaryStage{
        title = "Window title!"
        scene = new Scene(600,400) {
            val canvas = new Canvas(600,400)
            content = canvas
            val g = canvas.graphicsContext2D

            val path = getClass.getResource("/images/player.png")
            val img = new Image(path.toString)

            var xpos = 200
            var ypos = 100

            canvas.onKeyPressed = (e:KeyEvent) => {
                println("HEllo!")
                if(e.code == KeyCode.Space)
                    ypos += 5
            }
            canvas.requestFocus       

            val timer = AnimationTimer(t => {
                g.setFill(Color.White)
                g.fillRect(0,0,  600,400)

                g.strokeRect(200,100, 50,200)
                g.setFill(Color.Red)
                g.fillOval(200,100, 50,200)
                g.setFill(Color.rgb(255,255,0))
                g.fillText("Hello there Amanda!!", 200,100)

                g.drawImage(img, xpos,ypos)
                xpos += 1
            })
            timer.start


        }
    }
}