package cs2.particles

import scalafx.scene.canvas.GraphicsContext

class RainbowBackground(var w:Double, var h:Double) extends ColorRotation {
  def display(g:GraphicsContext):Unit = {
    g.fill = stepColor
    g.fillRect(0,0, w,h)
  }
}