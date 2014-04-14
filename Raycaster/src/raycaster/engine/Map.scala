/**
 *
 */
package raycaster.engine

import java.io.File
import java.util.Scanner
import scala.util.Try
/**
 *
 *
 * @author Vuorjoki Aleksi
 */
class Map(file: File) {
  private var w = 0
  private var h = 0
  private var nom = "default"
  private val src = new Scanner(file) //TODO: let's catch this on a upper level

  try {
    nom = file.getName()
    w = readAttribute(src.nextLine())
    h = readAttribute(src.nextLine())
  } catch {
    case nl: NoSuchElementException => System.err.println("The height or the width of the map not specified")
    case illegalArgument: IllegalArgumentException => System.err.println("Non valid width or height")
  }

  val width = w
  val height = h
  val name = nom.takeWhile(_ != '.')
  private val grid = Array.ofDim[Int](width, height)
  var count = 0
  try {
    for (row <- grid) {
      val line = src.nextLine()
      val len = line.length()
      count += 1
      if (len != row.length) {
        throw MapFormatException(count + ". row (" + line +") too short " + len + " != " + row.length)
      }
      for (i <- 0 until row.length) {
        if (line(i).isDigit) {
          row(i) = line(i).toInt - 48
        } else {
          throw MapFormatException("found a non-digit")
        }
      }
    }
  } catch {
    case mfe: MapFormatException => mfe.printStackTrace()
  }

  def gridVector = grid.map(_.toVector).toVector.reverse

  private def readAttribute(line: String): Int = {
    println(line)
    require(line(0).isLetter)
    require(!line(1).isLetterOrDigit)
    val value = line.takeRight(line.length() - 2)
    require(value.forall(_.isDigit))
    value.toInt
  }

}