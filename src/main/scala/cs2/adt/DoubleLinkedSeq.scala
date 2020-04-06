package cs2.adt

class DoubleLinkedSeq[A:Manifest] extends Seq[A] with Iterable[A] {
    private class Node(var data:A, var prev:Node, var next:Node) {
        def getNodeByIndex(idx:Int):Node = {
            if(idx >= 0 && idx < len) {
                var rover = this
                for(i <- 1 to idx) rover = rover.next
                rover
            } else end
        }
    }
    class BidirectionalIterator extends scala.collection.Iterator[A] {
        private var rover:Node = end.next
        def next():A = {
            val x:A = rover.data
            rover = rover.next
            x
        }
        def hasNext():Boolean = rover != end
        def previous():A = {
            val x:A = rover.data
            rover = rover.prev
            x
        }
        def hasPrevious():Boolean = rover != end
    }

    private var end:Node = new Node(new Array[A](1)(0) ,end,end)
    end.next = end
    end.prev = end
    private var len:Int = 0

    def iterator():BidirectionalIterator = {
        new BidirectionalIterator()
    }

    def get(idx:Int):A = end.next.getNodeByIndex(idx).data
    def set(idx:Int, elem:A):Unit = {
        end.next.getNodeByIndex(idx).data = elem
    }
    def insert(idx:Int, elem:A):Unit = {
        len += 1
        var rover = end.next.getNodeByIndex(idx-1)
        rover.next = new Node(elem, rover, rover.next)
        rover.next.next.prev = rover.next
    }
    override def append(elem:A):Unit = {
        len += 1
        var rover = end.prev
        rover.next = new Node(elem, rover, rover.next)
        rover.next.next.prev = rover.next
    }
    def remove(idx:Int):A = {
        var rover = end.next.getNodeByIndex(idx)
        rover.prev.next = rover.next
        rover.next.prev = rover.prev
        len -= 1
        rover.data
    }

    def length():Int = len

}