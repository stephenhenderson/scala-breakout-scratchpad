package com.cognitivematch.scratch.breakout

import java.awt.Graphics2D
import java.awt.Point
import java.awt.Color

class Ball(
    position : Point,
	fillColour : Color,
	alive : Boolean,
	velocity : Point,
	length : Int,
	height : Int
		) extends GameCharacter (position, fillColour, alive, velocity, length, height){

  def paint(g: Graphics2D): Unit = {
	 g.setColor(fillColour)
	 g.drawOval(position.x, position.y, length, height)
  }

  def interactWith(character: GameCharacter): Unit = {
	  character match {
	    case paddle : Paddle => {
	    	die
	    	println("Ball has died")
	    	}
	    case _ =>
	  }
  }

}