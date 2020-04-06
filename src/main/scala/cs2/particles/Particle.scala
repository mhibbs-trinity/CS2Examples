package cs2.particles

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

abstract class Particle(protected var pos:Vec2, protected var vel:Vec2) {
  
  def display(g:GraphicsContext):Unit
  
  def timeStep():Boolean = {
    pos += vel
    if(pos.y < -200 || pos.y > 1000) true
    else false
  }
  def applyForce(acc:Vec2):Unit = {
    vel += acc
  }
}