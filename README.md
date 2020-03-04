# Datos-Masivos

## Index
- [First Practice](#first-practice)
- [Second Practice (List)](#second-practice)
- [Third Practice (Fibonnacci)](#third-practice)
- [Four Practice](#four-practice)
- [Five Practice](#five-practice)
- [Six Practice](#six-practice)
- [Seven Practice](#seven-practice)

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

### Four Practice
**Pearson Correlation Research**

The Pearson Correlation Coefficient is a measure of the correspondence or linear relationship between two random quantitative variables. In simpler words it can be defined as an index used to measure the degree of relationship that two variables have, both quantitative.

Having two variables, the correlation facilitates estimates of the value of one of them, with knowledge of the value of the other variable.
This coefficient is a measure that indicates the relative situation of the events with respect to the two variables, that is, it represents the numerical expression that indicates the degree of correspondence or relationship that exists between the 2 variables. These numbers vary between limits of +1 and -1.

### Five Practice

import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header", "true").option("inferSchema","true")csv("Sales.csv")

1.sumDistinct
```
df.select(sumDistinct("Sales")).show() 
```
2.last
```
df.select(last("Company")).show()
```
3.first
```
df.select(first("Person")).show()
```
4.var_pop
```
df.select(var_pop("Sales")).show()
```
5.avg
```
df.select(avg("Sales")).show()
```
6.collect_list
```
df.select(collect_list("Sales")).show() 
```
7.var_samp
```
df.select(var_samp("Sales")).show() 
```
8.sum
```
df.select(sum("Sales")).show()
```
9.stddev_pop
```
df.select(stddev_pop("Sales")).show() 
```
10.skewness
```
df.select(skewness("Sales")).show()
 ```
 11.min
 ```
df.select(min("Sales")).show()
```
12.kurtosis
```
df.select(kurtosis("Sales")).show()
```
13.collect_set
```
df.select(collect_set("Sales")).show() 
```
14.approx_count_distinct
```
df.select(approx_count_distinct("Company")).show() 
```
15.mean
```
df.select(mean("Sales")).show() 
```
16.variance
```
df.select(variance ("Sales")).show() 
```
17.trunc
```
df.select(trunc("Sales")).show() 
```
18.bin
```
df.select(bin("Sales")).show() 
```
19.shuffle
```
df.select(shuffle("Sales")).show()
```
20.abs
```
df.select(abs("Sales")).show() 
```


### Six Practice

### Seven Practice

The variance serves to identify the mean of the quadratic deviations of a random variable,
considering the average value of it.

Simply put, we will define whether our generic type will include its sub-types and the way in which it will.Co-variance or covariance describes the flexibility in which one type can be considered a sub-type of another type in a generic definition.

Counter-variance or counter-variance is the opposite of covariance because it defines the flexibility of making a type, sub-type of its sub-type by the relationship that exists between them (example here).

And of course there is also the in-variance or invariance, which is not flexible and that limits the variance between types since if we define a generic class or trait as invariant then we will not include its sub-types in any way acting rigidly In the definition.

Parameters:
+ indicates covariance

- indicates countervariance

if not indicated, there is invariance in the type

Whereby:

+A indicates that the instantiated type must be type A itself or a subtype of A

-A indicates that the instantiated type must be type A itself or a supertype of A

 A indicates that the instantiated type must be type A itself
 
 
 Examples:
 
```
    abstract class Sequence[+A] {
                def append(x: Sequence[A]): Sequence[A]
                    // **** error: illegal variance:
                    // 'A' occurs in contravariant position.
            }
```
    
It fails because "append" forces supertype A
To correct it:

        
```
abstract class Sequence[+A] {
                def append[B >: A](x: Sequence[B]): Sequence[B]
            }
```
    
It works because "append" supports any subtype of A (which corresponds to + A).
         An example of countervariance:
         
```
        abstract class OutputChannel[-A]    {
                def write(x: A): Unit
            }
```


