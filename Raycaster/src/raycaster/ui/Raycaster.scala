/**
 *
 */
package raycaster.ui

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import java.awt.Dimension
import java.io.File

/**
 * @author OgreSound
 *
 */
object Raycaster extends SimpleSwingApplication {
    private val defaultWidth = 1280
    private val defaultHeight = 720
    private var width = defaultWidth
	private var height = defaultHeight
	private var mapFile = new File("RayCasterMapTextured.world")
	
  val mainWindow = new MainFrame {
	  title = "Raycaster"
	  minimumSize = new Dimension(width,height)
	  centerOnScreen()
	  resizable= false
	  val caster = new RaycasterPanel(width,height,mapFile)
	  contents = caster
	}
	
	def top = mainWindow
	
}