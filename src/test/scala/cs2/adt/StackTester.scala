package cs2.adt

import org.junit._
import org.junit.Assert._
import java.util._

class StackTester {
  var s:Stack[Int] = null
  
  @Before def initStack() {
    s = Stack[Int]()
  }
  
  @Test def checkIsEmptyWithEmpty() {
    assertTrue(s.isEmpty)
  }
  
  @Test def checkIsEmptyWithNonEmpty() {
    s.push(1)
    assertTrue(!s.isEmpty)
  }
  
  @Test def checkPushBeyondInitialSize() {
    for(i <- 0 until 30) {
      s.push((math.random*100).toInt)
    }
    assertTrue(!s.isEmpty)
  }
  
  @Test def checkPeek() {
    s.push(12)
    assertTrue(s.peek == 12)
    assertTrue(!s.isEmpty)
  }
  
  @Test def checkPop() {
    s.push(12)
    assertTrue(s.pop == 12)
    assertTrue(s.isEmpty)
  }

  @Test def checkPopFromEmpty() {
      try {
          s.pop
      } catch {
          case e:EmptyStackException => //expected
      }
  }

}