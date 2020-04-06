package cs2.particles

import cs2.util.Vec2
import scalafx.scene.image.Image
import scalafx.Includes._

class ImageParticleSystem(o:Vec2) extends ParticleSystem(o) {
  private val img = new Image("file:smoke.png")
  
  override def addParticle():Unit = {
    parts += new ImageParticle( Vec2.apply(origin), 
                     new Vec2(math.random*2 - 1, math.random*2 - 1),
                     img)
    
  }
}