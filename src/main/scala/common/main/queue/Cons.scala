package common.main.queue

class Cons[T](val head: T, val tail: List[T]) extends List[T]{
  override def isEmpty: Boolean = false
}
