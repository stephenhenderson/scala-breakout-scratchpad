package com.cognitivematch.scratch.breakout

import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics2D
import java.awt.Point
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import scala.swing.Button
import scala.swing.Component
import scala.swing.Frame
import scala.swing.MainFrame
import scala.swing.Panel
import scala.swing.SimpleSwingApplication
import scala.swing.event.Event
import scala.swing.event.Key
import scala.swing.event.KeyPressed
import scala.swing.event.MouseClicked
import java.awt.geom.Rectangle2D

class Breakout() extends Thread {

  val paddle: Paddle = new Paddle(new Point(100, 400), Color.BLUE, true, new Point(1, 0), 60, 20)
  val ball: Ball = new Ball(new Point(0, 0), Color.RED, true, new Point(1, 1), 10, 10)
  val characters = List(paddle,ball)
  val finished: AtomicBoolean = new AtomicBoolean(false)
  val gameCanvas = new Component {
    override def paint(g : Graphics2D):Unit = {
	  characters.foreach((character:GameCharacter) => character.paint(g))
    }
    focusable = true
    requestFocus
    listenTo(keys)
    reactions += {
      case KeyPressed(_, Key.Left, _, _) => movePaddleLeft  
      case KeyPressed(_, Key.Right, _, _) => movePaddleRight
    }
  } 

  def gameIteration():Unit = {
    paddle.move
    ball.move
    if (ball.overlaps(paddle)) {
      ball.reverseVerticalVelocity
    }
    TimeUnit.MILLISECONDS.sleep(5)
    gameCanvas.repaint
  }
	
  override def run = {
    
   while(!finished.get()) {
	    gameIteration
	  }
	}

  def movePaddleLeft(): Unit = {
    paddle.velocity = new Point(-1, 0)
  }

  def movePaddleRight(): Unit = {
    paddle.velocity = new Point(1, 0)
  }
}

object Breakout extends SimpleSwingApplication {
  val breakout = new Breakout()
  
  val startScreen = new Component {
    override def paint(g: Graphics2D): Unit = {
      g.setColor(Color.BLACK)
      g.fill(new Rectangle2D.Double(0, 0, 640, 480));
      g.setColor(Color.BLUE)
      g.setFont(new Font("Arial", Font.BOLD, 48))
      g.drawString("Breakout", 200, 200)

      g.setColor(Color.GREEN)
      g.setFont(new Font("Arial", Font.ITALIC, 20))
      g.drawString("Click to start", 200, 300)
    }
  }
  
  val gameFrame = new MainFrame {
    size = new Dimension(640,480)
    preferredSize = new Dimension(640,480)
    title = "Breakout"
    contents = startScreen
   }  
  
  listenTo(startScreen.mouse.clicks)
  reactions += {
    case MouseClicked(startScreen,_,_,_,_) => {
    	gameFrame.contents = breakout.gameCanvas
    	breakout.gameCanvas.focusable = true
		breakout.gameCanvas.requestFocus
    	breakout.start 
    }
  }
  
  def top = gameFrame
}
