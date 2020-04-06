package cs2.adt

import java.util._

class LinkedStack[A] extends Stack[A] {
    private class Node(var data:A, var next:Node)
    private var head:Node = null

    def push(elem:A):Unit = {
        head = new Node(elem, head)
    }
    def pop():A = {
        if(head == null) throw new EmptyStackException
        val ret = head.data
        head = head.next
        ret
    }
    def peek():A = {
        if(head == null) throw new EmptyStackException
        head.data
    }
    def isEmpty():Boolean = {
        head == null
    }
}