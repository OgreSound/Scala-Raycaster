/**
 *
 */
package raycaster.ui

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import scala.swing._
import java.awt.Dimension
import java.io.File
import scala.swing.FileChooser
import java.io.FileFilter
import javax.swing.filechooser.FileFilter
import javax.swing.filechooser.FileNameExtensionFilter


/**
 * @author OgreSound
 *
 */
object Raycaster extends SimpleSwingApplication {
    private val defaultWidth = 1280
    private val defaultHeight = 720
    private var width = defaultWidth
	private var height = defaultHeight
	private var mapFile = new File("maps/RayCasterMapTextured.world")
    private val path = mapFile.getCanonicalPath()
	val caster = new RaycasterPanel(width,height,mapFile)
  val mainWindow = new MainFrame {
	  title = "Raycaster"
	  minimumSize = new Dimension(width,height)
	  centerOnScreen()
	  resizable= false
	  
	  contents = caster
	  menuBar = new MenuBar {
	    contents += new Menu("Game"){
	      contents += new MenuItem(Action("load a map")(openMap))
	    }
	    
	  }
	}
	
	def top = mainWindow
	def openMap = {
	  val chooser = new FileChooser(new File(path))
	  chooser.fileFilter = new FileNameExtensionFilter("game maps", "world")
	  if(chooser.showOpenDialog(null) == FileChooser.Result.Approve){
	    caster.renderer.loadMap(chooser.selectedFile)
	  } 
	}
	
}