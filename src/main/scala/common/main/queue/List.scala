package common.main.queue

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}
