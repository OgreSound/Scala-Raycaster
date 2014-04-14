/**
 *
 */
package raycaster.ui

import scala.swing.Panel
import raycaster.engine.Renderer
import java.awt.Graphics2D
import java.awt.Dimension
import java.io.File

/**
 * @author OgreSound
 *
 */
class RaycasterPanel(val w: Int,val h: Int, val mapFile: File) extends Panel {
	maximumSize = new Dimension(w,h)
	
  val renderer = new Renderer(w,h,mapFile)
	
	override def paintComponent(g: Graphics2D) {
	  g.drawImage(renderer.render(g), null, 0, 0)
	}
}