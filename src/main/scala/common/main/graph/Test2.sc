class Rational(x:Int,y:Int) {

  require(y > 0, "Denominator must be non-zero")

  private def gcd(a:Int,b:Int):Int= if(b == 0) a else gcd(b,a%b)
  val numer = x / gcd(Math.abs(x),Math.abs(y))
  val denom = y / gcd(Math.abs(x),Math.abs(y))

  def +(that:Rational) = new Rational(numer*that.denom
    + denom*that.numer, denom*that.denom)
  def -(that:Rational) = this + !that
  def unary_! = new Rational(-x,y)
  def mkString = if(denom == 1) numer else numer + "/" + denom
}

val a = new Rational(3,2)
val b = (a + new Rational(1,5)).mkString
val d = (!a).numer
val c = (a - new Rational(1,2)).mkString


