# Datos-Masivos

## Index
- [First Practice](#first-practice)
- [Second Practice (List)](#second-practice)
- [Third Practice (Fibonnacci)](#third-practice)

### First Practice

1. Develop an algorithm in scala that calculates the radius of a circle
    ```
    var a=3
    var r=math.sqrt(a/math.Pi)
    ```
2. Develop an algorithm in scala that tells me if a number is a cousin
    ```
    def Prime(n:Int):Boolean={
        if(n%2==0){
            return false
        }
        else{
            for(i <- Range(3,n)){
                if(n%i==0){
                    return false
                }
            }
            return true                
        }
    }
    Prime(2)
    Prime(4)
    Prime(7)
    Prime(13)
    ```

3. Given the variable bird = "tweet", use string interpolation to
print "I am writing a tweet"

    ```
    var bird="tweet"
    printf(s"Estoy ecribiendo un %s",bird)
    ```

4. Given the variable message = "Hi Luke, I'm your father!" use slilce to extract the
sequence "Luke"

    ```
    var mensaje = "Hola Luke yo soy tu padre!"
    mensaje.slice(5,9)
    ```

5. What is the difference in value and a variable in scala?
    ```
    Cuando se utiliza value despues de haberlo declarado no se puede cambiar ya que este es inmutable.
    Una variable puede ser cambiada despues de haber sido declarada ya que esta es mutable.
    ```

6. Given the tuple ((2,4,5), (1,2,3), (3,114,23))) return the number 3.1416

    ```
    var t = ((2,4,5),(1,2,3),(3.1416,23))
    t._3._1
    ```

### Second Practice
1. Create a list called "list" with the elements "red", "white", "black"
 
     ```
     var lista = collection.mutable.MutableList("rojo","blanco","negro")
     ```

2. Add 5 more items to "list" "green", "yellow", "blue", "orange", "pearl"

    ```
    lista += ("verde","amarillo", "azul", "naranja", "perla")
    ```

3. Bring the elements of "list" "green", "yellow", "blue"
    
    ```
    lista(3)
    lista(4)
    lista(5)
    ```

4. Create a number array in the 1-1000 range in 5-in-5 steps
    ```
    var v = Range(1,1000,5)
    ```
5. What are the unique elements of the List list (1,3,3,4,6,7,3,7) use conversion to sets
    ```
    var l = List(1,3,3,4,6,7,3,7)
    l.toSet
    ```

6. Create a mutable map called names that contains the following
```"Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"```
    ```
    var map=collection.mutable.Map(("Jose", 20),("Luis", 24),("Ana", 23),("Susana", "27"))
    ```
6 a. Print all map keys
    ```
    map.keys
    ```
7 b. Add the following value to the map ("Miguel", 23)
    ```
    map += ("Miguel"->23)
    ``` 
 ### Third Practice
***Recursive Fibonnaci***
```
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
```
***Fibonacci complexity 0***
```
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
```
***Fibonacci loop***
```
def fib2( n : Int ) : Int = {
  var a = 0
  var b = 1
  var i = 0	  
 
  while( i < n ) {
    val c = a + b
    a = b
    b = c
    i = i + 1
  } 
  return a
}
println(fib2(n))
```
***Fibonnaci so-called tail-recursion***
```
def fib3( n : Int) : Int = { 
  def fib_tail( n: Int, a:Int, b:Int): Int = n match {
    case 0 => a 
    case _ => fib_tail( n-1, b, a+b )
  }
  return fib_tail( n, 0, 1)
}
println(fib3(n))
```
