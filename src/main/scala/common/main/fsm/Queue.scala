package common.main.fsm

class Queue[+A](in: List[A] = Nil, out: List[A] = Nil) {

  /**
    * Check whether this queue is empty or not.
    */
  def isEmpty: Boolean = (in, out) match {
    case (Nil, Nil) => true
    case (_, _) => false
  }

  /**
    * Dequeues the first element from this queue.
    *
    * Time - O(1)
    * Space - O(1)
    */
  def dequeue: (A, Queue[A]) = out match {
    case hd :: tl => (hd, new Queue(in, tl))
    case Nil => in.reverse match {
      case hd :: tl => (hd, new Queue(Nil, tl))
      case Nil => throw new NoSuchElementException("Empty queue.")
    }
  }

  /**
    * Enqueues given element 'x' into the end of this queue.
    *
    * Time - O(1)
    * Space - O(1)
    */
  def enqueue[B >: A](x: B): Queue[B] = new Queue(x :: in, out)

  /**
    * Returns the first element of this queue.
    *
    * Time - O(1)
    * Space - O(1)
    */
  def front: A = dequeue match { case (a, _) => a }

  /**
    * Returns the rear of this queue.
    *
    * Time - O(1)
    * Space - O(1)
    */
  def rear: Queue[A] = dequeue match { case (_, q) => q }
}

object Queue {

  /**
    * Creates a new empty queue.
    *
    * Time - O(1)
    * Space - O(1)
    */
  def empty[A]: Queue[A] = new Queue()

  /**
    * Creates a new queue fromm given 'xs' sequence.
    *
    * Time - O(n)
    * Space - O(1)
    */
  def apply[A](xs: A*) =
    xs.foldLeft(Queue.empty[A]) { case (acc, x) => acc.enqueue(x) }
}