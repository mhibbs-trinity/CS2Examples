package cs2.adt

import java.util._

trait Stack[A] {
    def push(elem:A):Unit

    @throws(classOf[EmptyStackException])
    def pop():A

    @throws(classOf[EmptyStackException])
    def peek():A
    
    def isEmpty():Boolean
}

object Stack {
    def apply[A : Manifest]():Stack[A] = new ArrayStack[A]()
}
