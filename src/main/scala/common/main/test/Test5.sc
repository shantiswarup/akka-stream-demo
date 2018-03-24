
import scala.collection.mutable.Queue
import scala.concurrent.Future

var puars = Queue[Int]()
Seq(1,2,3).foreach(puars.+=)
println(puars)
def process(puars:Queue[Int]):Unit = {
  if(puars.nonEmpty) {
    println(puars.dequeue())
    process(puars)
  } else {
    println("Finish")
  }
}

process(puars)




