package raycaster.tests
import org.scalatest._
import raycaster.math._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Trans2Test extends FlatSpec with Matchers {

  "*" should "transform the given vector" in {
    val vector = new Vec2(3,0)
    val trans = new Trans2(2,0,0,2)
    assume(trans.x1 == 2)
    assume(trans.x2 == 0)
    assume(trans.y1 == 0)
    assume(trans.x2 == 2)
    
    val transformed = trans * vector
    assert(transformed.x == 6)
    assert(transformed.y == 0) 
  } 
  
  "*" should "combine two transformations" in {
    val trans1 = new Trans2(2,0,0,2)
    val trans2 = new Trans2(-1,0,0,1)
    
    val combined = trans1 * trans2
  
    assert(combined.x1 == -2)
    assert(combined.x2 == 0)
    assert(combined.y1 == 0)
    assert(combined.y2 == 2)
  }
}