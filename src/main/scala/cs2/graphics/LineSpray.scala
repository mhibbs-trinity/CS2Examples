package cs2.graphics

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.scene.input.MouseEvent
import scalafx.scene.shape.StrokeLineCap

object LineSpray extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title = "Lines!"
        scene = new Scene(800,800) {
            val canvas = new Canvas(width.value,height.value)
            content = canvas

            val g = canvas.graphicsContext2D

            g.fill = Color.White
            g.fillRect(0,0, 800,800)

            canvas.onMouseDragged = (e:MouseEvent) => {
                g.strokeLine(400,400, e.x,e.y)
            }

            var px = 0.0
            var py = 0.0
            g.setLineCap(StrokeLineCap.ROUND)
            canvas.onMouseDragged = (e:MouseEvent) => {
                if(px != 0 || py != 0) {
                    g.setLineWidth(math.sqrt((px-e.x)*(px-e.x) + (py-e.y)*(py-e.y)))
                    g.strokeLine(px,py, e.x,e.y)
                }
                px = e.x
                py = e.y
            }
            
        }
    }
}