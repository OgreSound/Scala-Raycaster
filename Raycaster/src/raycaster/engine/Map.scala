/**
 *
 */
package raycaster.engine

import java.io.File
import java.util.Scanner
/**
 *
 *
 * @author Vuorjoki Aleksi
 */
class Map(file: File) {

  val name = file.getName()
  private val scr = new Scanner(file)
  private val width = scr.nextLine().toInt
  val height = scr.nextLine().toInt
  private val grid = Array.ofDim(width, height)

  private def readAttribute(line: String): Int = {
    ???
  }
}