package common.main.queue

class QNode[T](val value:T, val next: TNode[T]) extends TNode[T] {
  override def isEnd: Boolean = false
//  def this(value:T, next: TNode[T]) = new QNode(value,next)
  def this(value:T) = this(value, ENode)
}
