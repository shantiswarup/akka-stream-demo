package common.main.queue

trait TNode[+T] {
  def isEnd : Boolean
  def value : T
  def next : TNode[T]
}
