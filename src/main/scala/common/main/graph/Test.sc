def sum(f:Int=>Int,a:Int, b:Int): Int = {
  def loop(i:Int,acc:Int):Int={
    if(i>=b) acc else loop(i+1,acc+f(b))
  }
  loop(a,f(a))
}

val sumInts = sum(x=>2*x+1,4,5)

def sumtwo(f:Int=>Int,a:Int,b:Int):Int= {
  if(a>b) 0 else f(a) + sumtwo(f,a+1,b)
}

val x = sumtwo(x=>x,3,4)

def summ(f:Int=>Int)(a:Int,b:Int):Int = {
  if(a > b) 0 else f(a) + summ(f)(a+1,b)
}

val y: Int = summ(x=>x*2)(1,4)


def product(f:Int=>Int)(a:Int,b:Int) :Int = {
  if(a>b) 1 else f(a) * product(f)(a+1,b)
}
def fact(x:Int): Int = product(x=>x)(1,x)
fact(5)


def mapReduce(f:Int=>Int, combine:(Int,Int)=>Int, zero:Int)(a:Int,b:Int):Int = {
  if(a > b) zero else combine(f(a),mapReduce(f,combine,zero)(a+1,b))
}

def prod(f:Int=>Int)(a:Int,b:Int) = mapReduce(f,(x,y)=>x*y,1)(a,b)

def factorial(x:Int):Int = prod(x=>x)(1,x)

val r = factorial(5)



val tolerance = 0.0001
def isCloseEnough(x:Double, y:Double): Boolean = {
  Math.abs(((x-y)/x)/x) < tolerance
}

def fixedPoint(f:Double => Double)(firstGuess:Double) = {
  def iterate(guess:Double) :Double = {
    if(isCloseEnough(guess,f(guess)))
      guess
    else iterate(f(guess))
  }
  iterate(firstGuess)
}

def sqrt(x:Double) = {
  fixedPoint(y => (y + x / y)/2)(1)
}

val a = sqrt(2)
