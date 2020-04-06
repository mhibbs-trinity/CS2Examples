package cs2.particles

import scalafx.scene.paint.Color

trait ColorRotation {
  var hue:Double = 0
  
  def stepColor():Color = {
    hue += 2
    Color.hsb(hue, 1.0,1.0)
  }
}