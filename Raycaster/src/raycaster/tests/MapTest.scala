package raycaster.tests
import org.scalatest._
import raycaster.engine.Map
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import java.io.File
import scala.collection.immutable.Vector

@RunWith(classOf[JUnitRunner])
class MapTest extends FlatSpec with Matchers {

  "Map" should "fill the array properly" in {
    val level = new Map(new File("maps/testMap.world"))
    val goalGrid = {
      Vector(Vector(1, 1, 1, 1), 
    		 Vector(1, 0, 0, 1),
    		 Vector(1, 0, 1, 1),
    		 Vector(1, 1, 1, 1))
    }
    assert(level.name == "testMap")
    assert(level.gridVector == goalGrid)
  }
  
  //These two test actually never fail but let me see that a correct error is shown
  "Map" should "catch short row" in {
    val level = new Map(new File("maps/shortRow.world"))   
  }
  
  "Map" should "catch non-digit char in the map" in {
    val level = new Map(new File("maps/nonDigit.world"))
    
  }
}