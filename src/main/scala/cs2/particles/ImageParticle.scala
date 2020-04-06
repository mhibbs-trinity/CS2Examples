package cs2.particles

import cs2.util.Vec2
import scalafx.scene.image.Image
import scalafx.scene.canvas.GraphicsContext

class ImageParticle(p:Vec2, v:Vec2, private val img:Image) extends Particle(p, v) {
  override def display(g:GraphicsContext):Unit = {
    g.drawImage(img, pos.x, pos.y)
  }
}