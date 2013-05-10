package com.cognitivematch.scratch.breakout

import java.awt.Color
import java.awt.Dimension
import java.awt.Point
import scala.swing.Frame
import scala.swing.event.Event
import scala.swing.event.WindowClosed
import scala.swing.event.WindowClosing
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.TimeUnit
import java.util.Collections

class Breakout {
	val paddle : Paddle = new Paddle(new Point(100,400), Color.BLUE, true, new Point(1,0), 60, 20)
	val ball : Ball = new Ball(new Point(0,0), Color.RED, true, new Point(1,1), 10, 10)
	val characters = List(paddle,ball)
	val gameWindow : Frame = new Frame()
	val finished : AtomicBoolean = new AtomicBoolean(false)
	
	def setListenerToQuitWhenWindowClosed():Unit = {
	   val reaction : scala.swing.Reactions.Reaction = new scala.swing.Reactions.Reaction() {
	    	override def isDefinedAt(event:Event):Boolean = {
	    	  event match {
	    	    case wc:WindowClosing => true    
	    	    case _ => false
	    	  }
	    	}
	    	
	    	override def apply(event:Event):Unit = {
	    	  event match {
	    	    case wc:WindowClosing => finished.set(true)
	    	    case _ =>
	    	  } 
	    	}
	    }
	    gameWindow.reactions += reaction
	}
	
	def gameLoop():Unit = {
		println("Starting game loop")
		var moveRight:Boolean = true
		while(!finished.get()) {
			paddle.move
			ball.move
			if(ball.overlaps(paddle)) {
			  ball.reverseHorizontalVelocity
			  ball.reverseVerticalVelocity
			}
		    TimeUnit.MILLISECONDS.sleep(5)
		    gameWindow.repaint
		}
		System.exit(0)
	}
	
	def play():Unit = {
	    val canvas = new GameCanvas(ball, paddle)
	    setListenerToQuitWhenWindowClosed
	    gameWindow.contents = canvas
	    gameWindow.size = new Dimension(640,480)
		gameWindow.open
		gameLoop
	}
}

object Breakout {
  def main(args:Array[String]):Unit = {
     new Breakout().play
  }
}