package cs2.adt

class BinarySearchTree[A <% Ordered[A]] extends Iterable[A] {
  private class Node(var data: A, var left: Node, var right: Node) {
    def contains(elem: A): Boolean = {
      if (data == elem) true
      else if (elem < data) {
        if (left == null) false else left.contains(elem)
      } else {
        if (right == null) false else right.contains(elem)
      }
    }

    def insert(elem: A): Unit = {
      if (elem < data) {
        if (left == null) left = new Node(elem, null, null)
        else left.insert(elem)
      } else {
        if (right == null) right = new Node(elem, null, null)
        else right.insert(elem)
      }
    }

    def passUpMax(): (A, Node) = {
      if (right == null) (data, left)
      else {
        val (d, n) = right.passUpMax
        right = n
        (d, this)
      }
    }

    def remove(elem: A): Node = {
      if (elem == data) { //this is the node to remove
        if (left == null) right
        else if (right == null) left
        else {
          val (d, n) = left.passUpMax
          data = d
          left = n
          this
        }
      } else {
        if (elem < data && left != null) left = left.remove(elem)
        else if (right != null) right = right.remove(elem)
        this
      }
    }

  }

  private var root: Node = null

  def iterator():Iterator[A] = {
    new Iterator[A] {
      val stk = new LinkedStack[Node]()
      var curr = root

      def next():A = {
        while(curr != null) {
          stk.push(curr)
          curr = curr.left
        }
        curr = stk.pop
        val ret = curr.data
        curr = curr.right
        ret
      }
      def hasNext():Boolean = curr != null || !stk.isEmpty()
    }
  }

  override def isEmpty(): Boolean = {
    root == null
  }

  def contains(elem: A): Boolean = {
    if (root == null) false
    else root.contains(elem)
  }

  def insert(elem: A): Unit = {
    if (root == null) root = new Node(elem, null, null)
    else root.insert(elem)
  }

  def remove(elem: A): Unit = {
    if (root != null) root = root.remove(elem)
  }

  def printPreOrder(): Unit = {
    def processNode(curr: Node) {
      print(curr.data + ", ")
      if (curr.left != null) processNode(curr.left)
      if (curr.right != null) processNode(curr.right)
    }
    if (root != null) processNode(root)
    println()
  }

  def printInOrderStack():Unit = {
    val stk = new LinkedStack[Node]()
    var curr = root

    while(curr != null || !stk.isEmpty()) {
      while(curr != null) {
        stk.push(curr)
        curr = curr.left
      }
      curr = stk.pop
      print(curr.data + ", ")

      curr = curr.right
    }
    println
  }

}


object BinarySearchTreeStuff {
    def main(args:Array[String]):Unit = {
        val tree = new BinarySearchTree[Int]()
        tree.insert(10)
        tree.insert(5)
        tree.insert(20)
        tree.insert(3)
        tree.insert(15)
        tree.insert(8)
        tree.printPreOrder()
        
        tree.printInOrderStack()

        for(t <- tree) {
          println(t)
        }

    }
}
