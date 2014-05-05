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
  //  private val speed = 1
  //  private val rotSpeed = 90

  def position = pos

  def direction = dir

  def move(time: Double, up: Boolean, down: Boolean, left: Boolean, right: Boolean): Unit = {
    val moveSpeed = time * 5
    val rotSpeed = time * 3
    val rotationR = Trans2(cos(rotSpeed).toFloat, -sin(rotSpeed).toFloat, sin(rotSpeed).toFloat, cos(rotSpeed).toFloat)
    val rotationL = Trans2(cos(rotSpeed).toFloat, sin(rotSpeed).toFloat, -sin(rotSpeed).toFloat, cos(rotSpeed).toFloat)
    val map = renderer.world
    if (up) {
      val newPos = pos + dir * moveSpeed.toFloat
      if (!map.inWall(newPos + dir * .5f)) {
        pos = newPos
      }
    }
    if (down) {
      val newPos = pos + dir * (-moveSpeed.toFloat)
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