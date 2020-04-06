package cs2.adt

import java.util._

class ArrayStack[A : Manifest] extends Stack[A] {
    private var arr = new Array[A](10)
    private var len = 0

    def push(elem:A):Unit = {
        if(arr.length == len) {
            val tmp = new Array[A](len * 2)
            for(i <- 0 until len) {
                tmp(i) = arr(i)
            }
            arr = tmp
        }

        arr(len) = elem
        len += 1
    }

    def pop():A = {
        if(len == 0) {
            throw new EmptyStackException()
        }
        len -= 1
        arr(len)
    }

    def peek():A = { 
        if(len == 0) {
            throw new EmptyStackException()
        }
        arr(len-1)
    }
    
    def isEmpty():Boolean = { len == 0 }
}