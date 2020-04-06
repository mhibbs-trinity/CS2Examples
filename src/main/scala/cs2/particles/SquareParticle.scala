package cs2.particles

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

class SquareParticle (posZ:Vec2, velZ:Vec2) extends Particle(posZ, velZ) {
  override def display(g:GraphicsContext):Unit = {
    g.strokeRect(pos.x, pos.y, 10, 10)
  }
}