/**
 *
 */
package raycaster.math
/**
 * @author Vuorjoki Aleksi
 *
 */
case class Vec2( x: Float,  y: Float) {

  /**
   * Returns new vector that is this vector multiplied by the given coefficient
   *
   * @param the coefficient the vector should be multiplied by
   * @return a new multiplied vector
   */

  def *(coefficient: Float): Vec2 = {
    new Vec2(x * coefficient, y * coefficient)
  }
 
  /**
   * Returns a new vector that is the sum of this vector and the given vector
   *
   * @param the vector that should be added to this vector
   * @return the sum vector
   */
  
  def +(vector: Vec2): Vec2 = {
    new Vec2(this.x + vector.x , this.y + vector.y)
  }

  /**
	 * Returns a new vector resulting from subtracting the given vector from this vector
	 * 
	 * @param the vector that should be subtracted from this vector
	 * @return the subtracted vector
	 */
  
  def -(vector: Vec2): Vec2 = {
    new Vec2(this.x - vector.x , this.y - vector.y)
  }
  
  override def toString = this.x + " " + this.y
}