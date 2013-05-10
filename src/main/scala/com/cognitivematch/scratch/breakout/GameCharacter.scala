package com.cognitivematch.scratch.breakout

import java.awt.Point
import java.awt.Color
import java.awt.Graphics2D

abstract case class GameCharacter(
	var position : Point,
	fillColour : Color,
	var alive : Boolean,
	var velocity : Point,
	val length : Int,
	val height : Int
) {
	
  def paint(g : Graphics2D) : Unit
  def interactWith(character:GameCharacter):Unit
  
  def move():Unit = {
	val xpos = position.x
	val ypos = position.y
	if(xpos + length >= 640 || xpos < 0) {
	   reverseHorizontalVelocity
	}
	if(ypos + height >= 480 || ypos < 0) {
	   reverseVerticalVelocity
	}	
	position.x = position.x + velocity.x
	position.y = position.y + velocity.y
  }
  
  def overlaps(character:GameCharacter) : Boolean = {
    val leftMost = position.x
    val rightMost = position.x + length
    
    val top = position.y
    val bottom = position.y + height
    
    val xposThat = character.position.x
    val yposThat = character.position.y
    
    val horizontalOverlap:Boolean = 
      ((leftMost >= xposThat && leftMost <= xposThat + character.length)
      ||  (rightMost >= xposThat && rightMost <= xposThat + character.length))
      
    val verticalOverlap:Boolean = 
      ((top >= yposThat && top <= yposThat + character.height)
      ||  (bottom >= yposThat && bottom <= yposThat + character.height))
    
    return horizontalOverlap && verticalOverlap
  }
  
  def reverseHorizontalVelocity() : Unit = {
    velocity.x = -velocity.x
  }
  
  def reverseVerticalVelocity() : Unit = {
    velocity.y = -velocity.y
  }
  
  
  def die() {
    alive = false
  }
}