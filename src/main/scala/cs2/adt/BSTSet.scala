package cs2.adt

class BSTSet[A <% Ordered[A]] extends Set[A] {
    private var bst = new BinarySearchTree[A]()
    private var len = 0

    def iterator():Iterator[A] = bst.iterator

    def add(elem:A):Unit = {
        if(!bst.contains(elem)) {
            bst.insert(elem)
            len += 1
        }
    }
    def remove(elem:A):Unit = {
        if(bst.contains(elem)) {
            bst.remove(elem)
            len -= 1
        }
    }
    def contains(elem:A):Boolean = bst.contains(elem)
    def intersect(other:Set[A]):Set[A] = {
        val ret = new BSTSet[A]()
        for(elem <- bst) {
            if(other.contains(elem)) ret.add(elem)
        }
        ret
    }
    def union(other:Set[A]):Set[A] = {
        val ret = new BSTSet[A]()
        for(elem <- bst) ret.add(elem)
        for(elem <- other) ret.add(elem)
        ret
    }
    def difference(other:Set[A]):Set[A] = {
        val ret = new BSTSet[A]()
        for(elem <- bst) {
            if(!other.contains(elem)) ret.add(elem)
        }
        ret
    }
    override def size():Int = len
}