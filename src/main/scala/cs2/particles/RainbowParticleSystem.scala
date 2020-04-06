package cs2.particles

import cs2.util.Vec2

class RainbowParticleSystem(o:Vec2) extends ParticleSystem(o) {
  override def addParticle():Unit = {
    parts += new RainbowParticle(Vec2(origin), 
                           new Vec2(math.random*2 - 1, math.random*2 - 1))
  }
}