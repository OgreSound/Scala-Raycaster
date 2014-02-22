package raycaster.tests

import org.scalatest._
import raycaster.math.Vec2
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import raycaster.math.Vec2

@RunWith(classOf[JUnitRunner])
class Vec2Test extends FlatSpec with Matchers {

  "*" should "multiply vector with the given coefficient" in {
    val vector = new Vec2(4, 3)
    val coefficient: Float = 2
    
    assume(vector.x == 4)
    assume(vector.y == 3)
    
    val product = vector * coefficient
    
    
    assert(product.x == 8)
    assert(product.y == 6)
  }
  
  "+" should "add two vectors together" in {
    val vector1 = new Vec2(4,3)
    val vector2 = new Vec2(1,2)
    
    assume(vector1.x == 4)
    assume(vector1.y == 3)
    assume(vector2.x == 1)
    assume(vector2.y == 2)
    
    val sum = vector1 + vector2
    
//    assert(sum.x == 5)
//    assert(sum.y == 5)
    assert(sum == Vec2(5,5))
  }
  
  "-" should "should subtract vector2 from vector1" in {
    val vector1 = new Vec2(4,3)
    val vector2 = new Vec2(1,2)
    
    assume(vector1.x == 4)
    assume(vector1.y == 3)
    assume(vector2.x == 1)
    assume(vector2.y == 2)
    
    val substraction = vector1 - vector2
    
//    assert(substraction.x == 3)
//    assert(substraction.y == 1)
    assert(substraction == Vec2(3,1))
  }
}