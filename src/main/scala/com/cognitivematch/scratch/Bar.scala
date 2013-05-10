package com.cognitivematch.scratch

import javax.swing.JFrame
import scala.swing.Frame
import scala.swing.Button
import java.awt.Dimension
import scala.swing.Reactor
import java.awt.Canvas
import java.awt.Graphics
import java.awt.Color
import scala.swing.Component
import java.awt.Graphics2D

class Bar extends Component {
  
  override def paint(g: Graphics2D): Unit = {
    g.setColor(Color.BLUE)
    g.fillRect(0, 0, 100, 200)
  }
}

object Bar {
  def main(args:Array[String]) : Unit = {
		val foo1 = new Foo("test",10)
		val foo2 = new Foo("test",20)
		val canvas = new Bar()
		val mywindow = new Frame()
		
		
		mywindow.title = "My scala frame"
		mywindow.size =  new Dimension(200,300)
		mywindow.contents = canvas
		mywindow.open
		
		
		println("foo1 is older than foo2", foo1.isMoreAgedThan(foo2))
		println("foo2 is older than foo1", foo2.isMoreAgedThan(foo1))
		
		val foos = List(foo1)
	
	}
  
}