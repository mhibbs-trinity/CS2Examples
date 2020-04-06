package cs2.adt

class HashTableSet[A <% Ordered[A]] extends Set[A] {
  //Create the Array of BSTs to serve as the HashTable
  private var hash = Array.fill(100)(new BinarySearchTree[A]())
  private var len = 0

  private def getBucket(elem: A): Int = elem.hashCode % hash.length

  def add(elem: A): Unit = {
    val bucket = getBucket(elem)
    if (!hash(bucket).contains(elem)) {
      hash(bucket).insert(elem)
      len += 1
    }
  }
  def remove(elem: A): Unit = {
    val bucket = getBucket(elem)
    if (hash(bucket).contains(elem)) {
      hash(bucket).remove(elem)
      len -= 1
    }
  }
  def contains(elem: A): Boolean = {
    val bucket = getBucket(elem)
    hash(bucket).contains(elem)
  }
  override def size(): Int = len

  def intersect(other: Set[A]): Set[A] = {
    val res = new HashTableSet[A]()
    for (elem <- other) {
      if (contains(elem)) res.add(elem)
    }
    res
  }
  def union(other: Set[A]): Set[A] = {
    val res = new HashTableSet[A]()
    for (elem <- this) res.add(elem)
    for (elem <- other) res.add(elem)
    res
  }
  def difference(other: Set[A]): Set[A] = {
    val res = new HashTableSet[A]()
    for (elem <- this) {
      if (!other.contains(elem)) res.add(elem)
    }
    res
  }

  def iterator(): Iterator[A] = {
    new Iterator[A] {
      var bucket = 0
      var bstit = hash(0).iterator
      while (!bstit.hasNext && bucket < hash.length-1) {
        bucket += 1
        bstit = hash(bucket).iterator
      }

      def next(): A = {
        if (bstit.hasNext) bstit.next
        else {
          while (!bstit.hasNext && bucket < hash.length-1) {
            bucket += 1
            bstit = hash(bucket).iterator
          }
          bstit.next
        }
      }
      def hasNext(): Boolean = {
        if (bstit.hasNext) true
        else {
          while (!bstit.hasNext && bucket < hash.length-1) {
            bucket += 1
            bstit = hash(bucket).iterator
          }
          bstit.hasNext
        }
      }
    }
  }
}

object HashTableSetStuff {
  def main(args: Array[String]): Unit = {
    var s = new HashTableSet[Int]()
    for (i <- 1 to 10) s.add(i)
    var it = s.iterator
    while (it.hasNext) {
      println(it.next)
    }

    var t = new HashTableSet[Int]()
    for(i <- 2 to 10 by 2) t.add(i)
    it = t.iterator
    while(it.hasNext) println(it.next)

    var x = s.difference(t)
    it = x.iterator
    while(it.hasNext) println(it.next)

  }
}
