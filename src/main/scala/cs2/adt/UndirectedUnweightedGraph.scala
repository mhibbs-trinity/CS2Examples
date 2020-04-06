package cs2.adt

import scala.collection.mutable.Map

class UndirectedUnweightedGraph[A] {
    private class Node(var data:A, var edges:Seq[Node])
    private var nodes:Seq[Node] = new DoubleLinkedSeq[Node]()
    private var dataToNode:Map[A,Node] = Map()

    def addNode(elem:A):Unit = {
        if(!dataToNode.contains(elem)) {
            val createdNode = new Node(elem, new DoubleLinkedSeq[Node]())
            dataToNode(elem) = createdNode
            nodes.append(createdNode)
        }
    }

    def addEdge(left:A, right:A):Unit = {
        val leftNode = dataToNode(left)
        val rightNode = dataToNode(right)
        leftNode.edges.append(rightNode)
        rightNode.edges.append(leftNode)
    }

    

}