/**
 *
 */
package raycaster.engine

import raycaster.math._
import scala.math._
/**
 * @author OgreSound
 *
 */
class Player(private val renderer: Renderer) {
  val name = "player1"
  private val startPos = Vec2(1, 1)
  private val startDir = Vec2(1, 1)
  private var pos = startPos
  private var dir = startDir
  private val speed = 5
  private val rotSpeed = 3

  def position = pos

  def direction = dir

  def move(time: Double, up: Boolean, down: Boolean, left: Boolean, right: Boolean): Unit = {
    val movement = time * speed
    val rotation = time * rotSpeed
    val rotationR = Trans2(cos(rotation).toFloat, -sin(rotation).toFloat, sin(rotation).toFloat, cos(rotation).toFloat)
    val rotationL = Trans2(cos(rotation).toFloat, sin(rotation).toFloat, -sin(rotation).toFloat, cos(rotation).toFloat)
    val map = renderer.world
    
    if (up) {
      val newPos = pos + dir * movement.toFloat
      if (!map.inWall(newPos + dir * .5f)) { //checks that player stays at a minumum distance to a wall
        pos = newPos
      }
    }
    if (down) {
      val newPos = pos + dir * (-movement.toFloat)
      if (!map.inWall(newPos - dir * .5f)) {
        pos = newPos
      }
    }
    if (left) {

      dir = rotationL * dir
    }
    if (right) {

      dir = rotationR * dir
    }
  }
  def resetPosition = {
    pos = startPos
    dir = startDir
  }
}