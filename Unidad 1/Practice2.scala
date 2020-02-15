//Recursive Fibonnaci

val n = 10
def fibonacci1 (n:Int) : Int =
  {
    if (n<2){
    return n
  }
  else
  {
      return fibonacci1(n-1) + fibonacci1(n-2)
  }
}

println(fibonacci1(n))



// Fibonacci complexity 0
val n = 10
var phi=((1 + math.sqrt(5))/2)
var j=((math.pow(phi,n)-math.pow((1-phi),n))/(math.sqrt(5)))
def fibonacci1(n:Double) : Double ={
if (n<2)
  {
  return n
  }
  else
  {
    return j
  }
}

println(fibonacci1(n))
