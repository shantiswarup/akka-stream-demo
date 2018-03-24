package common.main.queue

object ENode extends TNode[Nothing] {
  override def isEnd: Boolean = true
  override def value: Nothing = throw new NoSuchElementException("End of list")
  override def next: Nothing = throw new NoSuchElementException("End of list")

}
