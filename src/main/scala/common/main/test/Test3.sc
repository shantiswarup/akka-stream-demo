abstract class  Boolean {
  def ifThenElse[T](t: => T,e: => T):T

  def &&(x: => Boolean) = ifThenElse(x, false)
  def ||(x: => Boolean) = ifThenElse(true, x)

  def unary_! = ifThenElse(false, true)

  def ==(x: =>Boolean) = ifThenElse(x, !x)
  def !=(x: =>Boolean) = ifThenElse(!x, x)

  def <(x: =>Boolean) = ifThenElse(false, x)
}

object True extends Boolean {
  def ifThenElse[T](t: =>T, e: =>T) = t
}

object False extends Boolean {
  def ifThenElse[T](f: =>T, e: =>T ) = e
}