package com.cognitivematch.scratch.breakout

import java.awt.Point
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Stroke
import java.awt.BasicStroke

class Paddle (
    position : Point,
	fillColour : Color,
	alive : Boolean,
	velocity : Point,
	length : Int,
	height : Int
		) extends GameCharacter (position, fillColour, alive, velocity, length, height){
	
	def paint(g:Graphics2D): Unit = {
		g.setColor(fillColour)
		g.drawRoundRect(position.x, position.y, length, height, 2, 2)
	}
	
	
	
	def interactWith(character: GameCharacter) : Unit = {
	  
	}
}