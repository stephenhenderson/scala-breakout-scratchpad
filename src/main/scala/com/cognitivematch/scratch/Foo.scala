package com.cognitivematch.scratch

/**
 * This is a class-level comment
 */
case class Foo(
    /** Name of the Foo*/
    name:String, 
    
    /** Age of the Foo in years*/
    age:Int
    ) {
  
	/**
	 * Is this foo older than another foo
	 * @param other other foo to compare age to
	 * @returns true if this foo is older than the other foo
	 */
	def isMoreAgedThan(other: Foo): Boolean = {
	  this.age > other.age
	}
  
	override def toString() : String = {
	  "Name: " + name + ",Age=" + age
	}
}