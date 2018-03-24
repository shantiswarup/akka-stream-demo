package common.main.queue


class Queue[T] {
  val front: ENode.type = ENode
  val rare: ENode.type = front

  def enqueue(value:T) = {
   val node = new QNode[T](value)
    if(front == ENode) {

    }
  }

  def isEmpty: Boolean = front == ENode && rare == ENode
}
