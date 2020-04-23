package cs2.adt

import org.junit._
import org.junit.Assert._
import java.util._

class SetTester {
  var s: Set[Int] = null
  var t: Set[Int] = null

  @Before def initSet() {
    s = Set[Int]()
    t = Set[Int]()
  }

  @Test def checkSizeWithEmpty() {
    assertTrue(s.size == 0)
  }

  @Test def checkInsertRemoveContainsSize() {
    for (i <- 1 to 10) {
      s.add(i)
      assertTrue(s.contains(i))
      assertTrue(s.size == i)
    }
    for (i <- 1 to 10) {
      s.add(i)
      assertTrue(s.contains(i))
      assertTrue(s.size == 10)
    }
    for (i <- 11 to 20) {
      s.remove(i)
      assertTrue(!s.contains(i))
      assertTrue(s.size == 10)
    }
    for (i <- 10 to 1 by -1) {
      s.remove(i)
      assertTrue(!s.contains(i))
      assertTrue(s.size == i - 1)
    }
  }

  @Test def checkIntersection() {
    for (i <- 1 to 10) {
      s.add(i)
    }
    for (i <- 2 to 10 by 2) {
      t.add(i)
    }
    val isect = s.intersect(t)
    assertTrue(isect.size == 5)
    for (i <- 2 to 10 by 2) {
      assertTrue(isect.contains(i))
    }
    for (i <- 1 to 9 by 2) {
      assertTrue(!isect.contains(i))
    }
  }

  @Test def checkDifference() {
    for (i <- 1 to 10) {
      s.add(i)
    }
    for (i <- 2 to 10 by 2) {
      t.add(i)
    }
    val diff = s.difference(t)
    assertTrue(diff.size == 5)
    
    for (i <- 2 to 10 by 2) {
      assertTrue(!diff.contains(i))
    }
    for (i <- 1 to 9 by 2) {
      assertTrue(diff.contains(i))
    }
  }

  @Test def checkUnion() {
    for (i <- 1 to 10) {
      s.add(i)
    }
    for (i <- 6 to 15) {
      t.add(i)
    }
    val uni = s.union(t)
    assertTrue(uni.size == 15)
    for (i <- 1 to 15) {
      assertTrue(uni.contains(i))
    }
    for (i <- 16 to 20) {
      assertTrue(!uni.contains(i))
    }
  }

}
