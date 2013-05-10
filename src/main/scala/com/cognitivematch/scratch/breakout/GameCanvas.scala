package com.cognitivematch.scratch.breakout

import scala.swing.Component
import java.awt.Graphics2D

class GameCanvas(characters:GameCharacter*) extends Component {
	
	override def paint(g : Graphics2D):Unit = {
	   characters.foreach((character:GameCharacter) => character.paint(g))
	} 
  
}