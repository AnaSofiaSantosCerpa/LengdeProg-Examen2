/*
CI-3641 Lenguajes de Programación I
Ana Santos 17-10602

                PARCIAL #2

Pregunta 4: Lenguaje escogido Kotlin
*/
import java.io.File
import java.util.*
import java.util.Scanner
import lib.programas.*

fun main(args: Array<String>) {
    var n = args[0].toInt()
    

    println("Subrutina Recursiva")
    var ti = System.currentTimeMillis()
    var fr = FR(n)
    var tf = System.currentTimeMillis()
    var t = tf - ti

    println("TIEMPO = $t")

    println("Subrutina Iterativa")
    var ti2 = System.currentTimeMillis()
    var fi = FI(n)
    var tf2 = System.currentTimeMillis()
    var t2 = tf2 - ti2
    
    println("TIEMPO = $t")

    if (t > t2) {
        println("El mejor tiempo es la Subrutina Recursiva")
    } else {
        println("El mejor tiempo es la Subrutina Iterativa")
    }
}