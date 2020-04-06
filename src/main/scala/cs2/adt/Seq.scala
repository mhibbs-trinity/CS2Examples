package cs2.adt

abstract class Seq[A] {
    def get(idx:Int):A
    def set(idx:Int, elem:A):Unit
    def insert(idx:Int, elem:A):Unit
    def remove(idx:Int):A

    def length():Int

    //Concrete methods dependent on the abstract methods
    def prepend(elem:A):Unit = insert(0,elem)
    def append(elem:A):Unit = insert(length,elem)
    def apply(idx:Int):A = get(idx)
    def update(idx:Int,elem:A):Unit = set(idx,elem)
}