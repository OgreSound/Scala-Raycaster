/**
 *
 */
package raycaster.engine
import java.awt.Graphics2D
import java.awt.image.BufferedImage._
import java.awt.image.BufferedImage
import java.awt.Color
/**
 * @author OgreSound
 *
 */
class Renderer(private var w: Int, private var h: Int) {

  def render(g: Graphics2D): BufferedImage = {
    val img = new BufferedImage(w,h,TYPE_INT_ARGB)
    val gr = img.createGraphics()
    gr.setColor(Color.black)
    gr.fillRect(0, 0, w, h)
    gr.setColor(Color.green)
    gr.drawOval(20, 20, 100,100)
    img
  }
}
