/**
 *
 */
package raycaster.math

/**
 * Represents a 2x2 matrix
 * 
 * [x1 x2]
 * [y1 y2]
 * 
 * Used for transforming vectors
 * 
 * @author Vuorjoki Aleksi
 *
 */
class Trans2(val x1: Float, val x2: Float, val y1: Float, val y2: Float) {

  def *(vector: Vec2): Vec2 = {
    val newX = this.x1 * vector.x + this.x2 * vector.y
    val newY = this.y1 * vector.x + this.y2 * vector.y
    new Vec2(newX, newY)
  }

  /**
   * Combines two transformations so that the transformation given as a parameter will be applied first
   * @param transformations matrix that will be applied first
   * @return combined transformation
   */
  def *(trans: Trans2): Trans2 = {
    val newX1 = this.x1 * trans.x1 + this.x2 * trans.y1
    val newX2 = this.x1 * trans.x2 + this.x2 * trans.y2
    val newY1 = this.y1 * trans.x1 + this.y2 * trans.y1
    val newY2 = this.y1 * trans.x2 + this.y2 * trans.y2
    new Trans2(newX1, newX2, newY1, newY2)
  }
}