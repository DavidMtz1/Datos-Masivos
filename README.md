# Datos-Masivos

##PRACTICA 1
//1. Desarrollar un algoritmo en scala que calcule el radio de un circulo
var a=3
var r=math.sqrt(a/math.Pi)

//2. Desarrollar un algoritmo en scala que me diga si un numero es primo
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


//3. Dada la variable bird = "tweet", utiliza interpolacion de string para
//   imprimir "Estoy ecribiendo un tweet"
var bird="tweet"
printf(s"Estoy ecribiendo un %s",bird)

//4. Dada la variable mensaje = "Hola Luke yo soy tu padre!" utiliza slilce para extraer la
//   secuencia "Luke"
var mensaje = "Hola Luke yo soy tu padre!"
mensaje.slice(5,9)

//5. Cual es la diferencia en value y una variable en scala?
//Cuando se utiliza value despues de haberlo declarado no se puede cambiar ya que este es inmutable.
//Una variable puede ser cambiada despues de haber sido declarada ya que esta es mutable.


//6. Dada la tupla ((2,4,5),(1,2,3),(3.1416,23))) regresa el numero 3.1416 
var t = ((2,4,5),(1,2,3),(3.1416,23))
t._3._1


##PRACTICA 2
// 1. Crea una lista llamad "lista" con los elementos "rojo", "blanco", "negro"
    var lista = collection.mutable.MutableList("rojo","blanco","negro")

// 2. Añadir 5 elementos mas a "lista" "verde" ,"amarillo", "azul", "naranja", "perla"
    lista += ("verde","amarillo", "azul", "naranja", "perla")


// 3. Traer los elementos de "lista" "verde", "amarillo", "azul"
    lista(3)
    lista(4)
    lista(5)
    

// 4. Crea un arreglo de numero en rango del 1-1000 en pasos de 5 en 5
    var v = Range(1,1000,5)
    
    // 5. Cuales son los elementos unicos de la lista Lista(1,3,3,4,6,7,3,7) utilice conversion a conjuntos
    var l = List(1,3,3,4,6,7,3,7)
    l.toSet


// 6. Crea una mapa mutable llamado nombres que contenga los siguiente
//     "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
    var map=collection.mutable.Map(("Jose", 20),("Luis", 24),("Ana", 23),("Susana", "27"))
// 6 a . Imprime todas la llaves del mapa
    map.keys


// 7 b . Agrega el siguiente valor al mapa("Miguel", 23)
    map += ("Miguel"->23)
    
    
 ##PRACTICA 3
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

//fibonacci loop
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

//fibonnaci so-called tail-recursion
def fib3( n : Int) : Int = { 
  def fib_tail( n: Int, a:Int, b:Int): Int = n match {
    case 0 => a 
    case _ => fib_tail( n-1, b, a+b )
  }
  return fib_tail( n, 0, 1)
}
println(fib3(n))
