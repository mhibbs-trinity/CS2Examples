package cs2.adt

abstract class Set[A] extends Iterable[A] {
    //Abstract methods required for all Sets
    def add(elem:A):Unit
    def remove(elem:A):Unit
    def contains(elem:A):Boolean
    def size():Int

    def intersect(other:Set[A]):Set[A]
    def union(other:Set[A]):Set[A]
    def difference(other:Set[A]):Set[A]
    

    //Concrete methods derived from the abstract methods
    def += (elem:A) = add(elem)
    def -= (elem:A) = remove(elem)
    def & (other:Set[A]):Set[A] = intersect(other)
    def ++ (other:Set[A]):Set[A] = union(other)
    def -- (other:Set[A]):Set[A] = difference(other)
}

object Set {
    def apply[A <% Ordered[A]]():Set[A] = new HashTableSet[A]()
}