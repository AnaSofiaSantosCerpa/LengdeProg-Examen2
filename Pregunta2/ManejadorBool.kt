/*
CI-3641 Lenguajes de Programación I
Ana Santos 17-10602

                PARCIAL #2

Pregunta 2: Lenguaje escogido Kotlin
*/
import java.io.File
import java.util.*
import java.util.Scanner
import lib.programas.*

fun main(args: Array<String>) {
    var salir = true

    while (salir) {
        print("ACCION> ")
        var accion = readLine()!!
        val sincomillas = accion.split(" ")
        var action = sincomillas[0]

        if (action == "EVAL") {
            var tipo = sincomillas[1]

            if (tipo == "PRE") {
                var operaciones = mutableListOf<String>()
                var expresiones = mutableListOf<Boolean>()
                var size = sincomillas.size
                var respuesta = false

                for (i in 2..size-1) {
                    if (sincomillas[i] == "true" || sincomillas[i] == "false") {
                        expresiones.add(sincomillas[i].toBoolean())
                    } else {
                        operaciones.add(sincomillas[i])
                    }
                }

                respuesta = Prefijo(operaciones,expresiones).resultado
                println(respuesta)
                
            } else if (tipo == "POST") {
                println(tipo)
            }                
        }

        if (action == "MOSTRAR") {
            var tipo = sincomillas[1]

            if (tipo == "PRE") {
                var operaciones = mutableListOf<String>()
                var expresiones = mutableListOf<Boolean>()
                var size = sincomillas.size

                for (i in 2..size-1) {
                    if (sincomillas[i] == "true" || sincomillas[i] == "false") {
                        expresiones.add(sincomillas[i].toBoolean())
                    } else {
                        operaciones.add(sincomillas[i])
                    }
                }

                var str = Prefijo(operaciones,expresiones).mostrar()
                println(str)
            }
        }
        
        if (action == "SALIR") {
            println("~~~")
            salir = false
        }
    }
}