/**
 *
 */
package raycaster.engine
import java.awt.Graphics2D
import java.awt.image.BufferedImage._
import java.awt.image.BufferedImage
import java.awt.Color
import java.io.File
import raycaster.math.Vec2
import scala.math._
/**
 * @author OgreSound
 *
 */
class Renderer(private var w: Int, private var h: Int, private var mapFile: File) {

  private var map = new Map(mapFile)
  val world = map
  val player = new Player(this)
  
  private def plane = Vec2(-player.direction.y, player.direction.x)

  def loadMap(mapFile:File) ={
    map = new Map(mapFile)
    player.resetPosition
  } 
  
  def render(g: Graphics2D): BufferedImage = {
    val img = new BufferedImage(w, h, TYPE_INT_ARGB)
    val gr = img.createGraphics()
    gr.setColor(Color.black)
    gr.fillRect(0, 0, w, h)
    
    for(x <- 0 until w) {
      val cameraX: Float = 2f * x / w - 1
      val rayPos = Vec2(player.position.x, player.position.y)
      val rayDir = player.direction + (plane * cameraX)
      //the position of the ray in the map coordinates
      var mapX: Int = rayPos.x.toInt
      var mapY: Int = rayPos.y.toInt

      //distance the ray travels from one x/y-side to the next x/y-side
      val deltaDistX = sqrt(1 + (rayDir.y * rayDir.y) / (rayDir.x * rayDir.x))
      val deltaDistY = sqrt(1 + (rayDir.x * rayDir.x) / (rayDir.y * rayDir.y))

      var sideDistX: Double = 0
      var sideDistY: Double = 0
      var stepX: Int = 0
      var stepY: Int = 0

      if (rayDir.x < 0) {
        stepX = -1;
        sideDistX = (rayPos.x - mapX) * deltaDistX;
      } else {
        stepX = 1;
        sideDistX = (mapX + 1.0 - rayPos.x) * deltaDistX;
      }

      if (rayDir.y < 0) {
        stepY = -1;
        sideDistY = (rayPos.y - mapY) * deltaDistY;
      } else {
        stepY = 1;
        sideDistY = (mapY + 1.0 - rayPos.y) * deltaDistY;
      }
      //which side was hit: x = false , y = true
      var side = true

      var hit = false
      while (!hit) {
        if (sideDistX < sideDistY) {
          sideDistX += deltaDistX;
          mapX += stepX;
          side = false;
        } else {
          sideDistY += deltaDistY;
          mapY += stepY;
          side = true;
        }
        if (map.gridVector(mapX)(mapY) > 0) hit = true
      }
      var correctDist = 0f
      if (!side) {
        correctDist = abs((mapX - rayPos.x + (1 - stepX) / 2) / rayDir.x)
      } else {
        correctDist = abs((mapY - rayPos.y + (1 - stepY) / 2) / rayDir.y)
      }
      val sliceHeight = abs((h) / correctDist).toInt

      val drawStart = max(-sliceHeight / 2 + h / 2, 0)
      val drawEnd = min(sliceHeight / 2 + h / 2, h)
      var color = map.gridVector(mapX)(mapY) match {
        case 1 => Color.red
        case 2 => Color.green
        case 3 => Color.blue
        case 4 => Color.orange
        case 5 => Color.yellow
      }
      
      var components = color.getComponents(Array(0f, 0f, 0f, 0f))

      components = components.map(float => max(0, min(1, float * ((1 / correctDist) * 2))))

      color = new Color(components(0), components(1), components(2))
      if (side) {
        color = color.brighter()
      } else {
        color = color.darker()
      }
      gr.setColor(color)
      gr.drawLine(x, drawStart, x, drawEnd)
    }
    img
  }
}
