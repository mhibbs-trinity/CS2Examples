package cs2.particles

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class RainbowParticle(p:Vec2, v:Vec2) extends RoundParticle(p,v) with ColorRotation {
  
  override def display(g:GraphicsContext):Unit = {
    g.stroke = stepColor
    super.display(g)
  }
  
}