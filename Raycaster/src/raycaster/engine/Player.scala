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
class Player {
  val name = "player1"
  private var pos = Vec2(1, 1)
  private var dir = Vec2(1, 1)
//  private val speed = 1
//  private val rotSpeed = 90
 
  
  
  def position = pos

  def direction = dir

  
  def move(time: Double,up: Boolean, down: Boolean, left: Boolean, right:Boolean): Unit = {
    val moveSpeed = time * 5
    val rotSpeed = time * 3
    val rotationR = Trans2(cos(rotSpeed).toFloat,-sin(rotSpeed).toFloat,sin(rotSpeed).toFloat,cos(rotSpeed).toFloat)
    val rotationL = Trans2(cos(rotSpeed).toFloat,sin(rotSpeed).toFloat,-sin(rotSpeed).toFloat,cos(rotSpeed).toFloat)
    if(up){
      pos += dir*moveSpeed.toFloat
    }
    if(down) {
      pos += dir*(-moveSpeed.toFloat)
    }
    if(left){
      println(dir)
      dir = rotationL*dir
    }
    if(right){
      println(dir)
      dir = rotationR*dir
    }
  }
}