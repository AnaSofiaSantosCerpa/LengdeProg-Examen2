/*
CI-3641 Lenguajes de Programación I
Ana Santos 17-10602

                PARCIAL #2

Pregunta 5: Lenguaje escogido Kotlin
*/
import java.io.File
import java.util.*
import java.util.Scanner
import lib.programas.*

fun main(args: Array<String>) {
    var salir = true
    var listaAt = mutableListOf<Atomico>()
    var listaSt = mutableListOf<Struct>()
    var listaU = mutableListOf<Union>()

    while (salir) {
        print("ACCION> ")
        var accion = readLine()!!
        val sincomillas = accion.split(" ")
        var action = sincomillas[0]

        if (action == "ATOMICO") {
            var nombre = sincomillas[1]
            var buscar = listaAt.find {it.n == nombre}

            if (buscar != null) {
                println("ERROR: '$nombre' ya existe") 
            } else {
                var rep = sincomillas[2].toInt()
                var ali = sincomillas[3].toInt()
                listaAt.add(Atomico(nombre,rep,ali))
            }                
        }

        if (action == "STRUCT") {
            var nombre = sincomillas[1]
            var buscar0 = listaAt.find {it.n == nombre}
            var buscar2 = listaSt.find {it.n == nombre}
            var aux = mutableListOf<String>()

            if (buscar0 != null || buscar2 != null) {
                println("ERROR: '$nombre' ya existe") 
            } else {
                var sub = sincomillas.subList(2,sincomillas.size)
                for (i in sub) {
                    var buscar1 = listaAt.find {it.n == i}
                    if (buscar1 == null) {
                        println("ERROR: '$i' no existe") 
                    } else {
                        aux.add(i)
                    }
                }
                if (aux.size == sub.size) {
                    listaSt.add(Struct(nombre,aux,listaAt))
                }      
            }                
        }

        if (action == "UNION") {
            var nombre = sincomillas[1]
            var buscar0 = listaAt.find {it.n == nombre}
            var buscar3 = listaU.find {it.n == nombre}
            var aux = mutableListOf<String>()

            if (buscar0 != null || buscar3 != null) {
                println("ERROR: '$nombre' ya existe") 
            } else {
                var sub = sincomillas.subList(2,sincomillas.size)
                for (i in sub) {
                    var buscar1 = listaAt.find {it.n == i}
                    var buscar2 = listaSt.find {it.n == i}
                    if (buscar1 == null && buscar2 == null) {
                        println("ERROR: '$i' no existe") 
                    } else {
                        aux.add(i)
                    }
                }
                if (aux.size == sub.size) {
                    listaU.add(Union(nombre,aux,listaAt,listaSt))
                }      
            }                
        } 

        if (action == "DESCRIBIR"){
            var nombre = sincomillas[1]
            var buscar1 = listaAt.find {it.n == nombre}
            var buscar2 = listaSt.find {it.n == nombre}
            var buscar3 = listaU.find {it.n == nombre}

            if (buscar1 == null && buscar2 == null && buscar3 == null) {
                println("ERROR: $nombre no es un programa creado")
            } else {
                if (buscar1 != null) {
                    println("Información cuando se guarda $nombre: ")
                    var f = buscar1.representacion()
                    var s = buscar1.alineacion()
                    var t = s-f
                    println("TAMAÑO = $f")
                    println("ALINEACION = $s")
                    println("DESPERDICIO = $t")
                }
                
                if (buscar2 != null) {
                    var sin = buscar2.sinEmpaquetar()
                    var em = buscar2.empaquetar()
                    var re = buscar2.reordenar()
                    
                    println("Información cuando se guarda $nombre sin empaquetar: ")
                    var f = sin.first
                    var s = sin.second
                    var t = sin.third
                    println("TAMAÑO = $f")
                    println("ALINEACION = $s")
                    println("DESPERDICIO = $t")

                    println("Información cuando se guarda $nombre empaquetando: ")
                    f = em.first
                    s = em.second
                    t = em.third
                    println("TAMAÑO = $f")
                    println("ALINEACION = $s")
                    println("DESPERDICIO = $t")

                    println("Información cuando se guarda $nombre reordenando los campos de manera óptima: ")
                    f = re.first
                    s = re.second
                    t = re.third
                    println("TAMAÑO = $f")
                    println("ALINEACION = $s")
                    println("DESPERDICIO = $t")
                } 

                if (buscar3 != null) {
                    var sin = buscar3.sinEmpaquetarU()
                    var em = buscar3.empaquetarU()
                    var re = buscar3.reordenarU()
                    
                    println("Información cuando se guarda $nombre sin empaquetar: ")
                    var f = sin.first
                    var s = sin.second
                    var t = sin.third
                    println("TAMAÑO = $f")
                    println("ALINEACION = $s")
                    println("DESPERDICIO = $t")

                    println("Información cuando se guarda $nombre empaquetando: ")
                    f = em.first
                    s = em.second
                    t = em.third
                    println("TAMAÑO = $f")
                    println("ALINEACION = $s")
                    println("DESPERDICIO = $t")

                    println("Información cuando se guarda $nombre reordenando los campos de manera óptima: ")
                    f = re.first
                    s = re.second
                    t = re.third
                    println("TAMAÑO = $f")
                    println("ALINEACION = $s")
                    println("DESPERDICIO = $t")
                    
                }
                
            }
        }       
                
        if (action == "SALIR") {
            println("~~~")
            salir = false
        }
    }
}