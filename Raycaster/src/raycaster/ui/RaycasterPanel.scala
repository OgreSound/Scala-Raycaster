/**
 *
 */
package raycaster.ui

import scala.swing.Panel
import raycaster.engine.Renderer
import java.awt.Graphics2D
import java.awt.Color
import java.awt.Dimension
import java.io.File
import javax.swing.Timer
import scala.swing.event._

/**
 * @author OgreSound
 *
 */
class RaycasterPanel(val w: Int, val h: Int, val mapFile: File) extends Panel {
  maximumSize = new Dimension(w, h)
  val renderer = new Renderer(w, h, mapFile)
  private var lastUpdateTime = System.nanoTime()
  private var fps = 0.0
  private val timer = new Timer(16, swing.Swing.ActionListener(
    e => {
      val currentTime = System.nanoTime()
      
      renderer.player.move(((currentTime - lastUpdateTime)) / 1e9,up,down,left,right)
      fps = 1 /((currentTime - lastUpdateTime)/ 1e9)
      this.repaint
      lastUpdateTime = currentTime
    }))
  timer.start
 
  override def paintComponent(g: Graphics2D) {
    g.drawImage(renderer.render(g), null, 0, 0)
    g.setColor(Color.white)
    g.drawString("Fps: " + fps, 20, 20)
  }
  private var up = false
  private var down = false
  private var left = false
  private var right = false

  this.opaque = false

  
  listenTo(keys)
  focusable = true
  requestFocus
  reactions += {
    case KeyPressed(_, Key.Up, _, _) => up = true
    case KeyPressed(_, Key.Down, _, _) => down = true
    case KeyPressed(_, Key.Left, _, _) =>  left = true
    case KeyPressed(_, Key.Right, _, _) =>  right = true
    case KeyReleased(_, Key.Up, _, _) => up = false
    case KeyReleased(_, Key.Down, _, _) => down = false
    case KeyReleased(_, Key.Left, _, _) =>  left = false
    case KeyReleased(_, Key.Right, _, _) =>  right = false
  }
}