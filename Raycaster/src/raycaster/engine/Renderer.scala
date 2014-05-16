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
import javax.imageio.ImageIO
import java.awt.Image
/**
 * @author OgreSound
 *
 */
class Renderer(private var w: Int, private var h: Int, private var mapFile: File) {
  private var map = new Map(mapFile)
  def world = map
  val player = new Player(this)
  def pos = player.position
  def dir = player.direction
  def plane = Vec2(-dir.y, dir.x)
  val textures: Array[BufferedImage] = Array.ofDim(8)
  for (i <- textures.indices) {
    i match {
      case 0 => textures(i) = ImageIO.read(new File("pics/redbrick.png"))
      case 1 => textures(i) = ImageIO.read(new File("pics/wood.png"))
      case 2 => textures(i) = ImageIO.read(new File("pics/colorstone.png"))
      case 3 => textures(i) = ImageIO.read(new File("pics/eagle.png"))
      case 4 => textures(i) = ImageIO.read(new File("pics/bluestone.png"))
      case 5 => textures(i) = ImageIO.read(new File("pics/mossy.png"))
      case 6 => textures(i) = ImageIO.read(new File("pics/greystone.png"))
      case 7 => textures(i) = ImageIO.read(new File("pics/purplestone.png"))
    }
  }

  def loadMap(mapFile: File) = {
    map = new Map(mapFile)
    player.resetPosition
  }
  def render(g: Graphics2D): BufferedImage = {
    val img = new BufferedImage(w, h, TYPE_INT_ARGB)
    val gr = img.createGraphics()
    gr.setColor(Color.black)
    gr.fillRect(0, 0, w, h)
    var x = 0
    while (x < w) {
      val cameraX: Float = 2f * x / w - 1

      val rayPos = Vec2(pos.x, pos.y)
      val rayDir = dir + (plane * cameraX)
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
      //which side was hit: x/horizontal = false , y/vertical = true
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
      val sliceHeight = abs((h/2) / correctDist).toInt

      val drawStart = max(-sliceHeight / 2 + h / 2, 0)
      val drawEnd = min(sliceHeight / 2 + h / 2, h)
      val mapValue = map.gridVector(mapX)(mapY)
      val texture = textures(mapValue - 1)
      val hitPos = rayPos + Vec2(sideDistX.toFloat, sideDistY.toFloat)
      var wallX = side match {
        case true => rayPos.x + ((mapY - rayPos.y + (1 - stepY) / 2) / rayDir.y) * rayDir.x
        case false => rayPos.y + ((mapX - rayPos.x + (1 - stepX) / 2) / rayDir.x) * rayDir.y
      }

      wallX -= wallX.toInt
      val th = texture.getHeight()
      val tw = texture.getWidth()
      var sliceStart = (tw * wallX).toInt

      if (rayDir.x > 0 && side == false || rayDir.y < 0 && side == true) {
        sliceStart = tw - sliceStart - 1
      }
      for (y <- drawStart until drawEnd) {
        val d = y * 256 - h * 128 + sliceHeight * 128
        val texY = ((d * th) / sliceHeight) / 256
        

        val color = texture.getRGB(sliceStart, texY.toInt)

        img.setRGB(x, y, color)
      }
      x += 1
    }
    img
  }
}
