package lib.programas

public class Bloques(val l: MutableList<Atomico>) {


    fun sinEmpaquetar() : Triple<Int,String,Int> {
        var ocupacion = 0
        var ali = ""
        var desperdicio = 0
        var bloques = mutableListOf<Triple<String,Int,Int>>()
        var cont = -1

        l.forEach {it ->
            ocupacion += it.representacion()

            if (bloques.isEmpty()) {
                if (it.alineacion() % 4 == 0) {
                    var cantBlq = it.alineacion() / 4
                    var rep = it.representacion()
                    for (i in 0..cantBlq-1) {
                        cont += 1
                        if (rep >= 4) {
                            rep -= 4
                            bloques.add(Triple(it.name(),cont,4))
                        } else if (rep < 4 && rep > 0) {
                            bloques.add(Triple(it.name(),cont,rep))
                            bloques.add(Triple(vacio,cont,4-rep))
                            rep = 0
                        } else if (rep == 0) {
                            bloques.add(Triple(vacio,cont,4-rep))
                        }
                    }
                } else {
                    var cantBlq = it.alineacion() / 4
                    if (cantBlq == 0) {
                        cont += 1
                        var dif = it.alineacion() - it.representacion()
                        if (dif == 0) {
                            bloque.add(Triple(it.name(),cont,it.representacion())) 
                        } else {
                            bloque.add(Triple(it.name(),cont,it.representacion())) 
                            bloque.add(Triple(vacio,cont,dif)) 
                        }
                    } else {
                        var rep = it.representacion()
                        var acum = 0
                        for (i in 0..cantBlq) {
                            cont += 1
                            if (rep >= 4) {
                                rep -= 4
                                acum += 4
                                bloques.add(Triple(it.name(),cont,4))
                            } else if (rep < 4 && rep > 0) {
                                bloques.add(Triple(it.name(),cont,rep))
                                rep = 0
                                acum += rep
                                if (acum < it.alineacion()) {
                                    var dif = 4 - (it.alineacion() - acum)
                                    bloques.add(Triple(vacio,cont,dif))
                                    acum += 0
                                }
                            } else if (rep == 0) {
                                var dif = it.alineacion() - acum
                                bloques.add(Triple(vacio,cont,dif))
                            }
                        }
                    }
                }
            } else if (it == l[l.size-1]) {
                if (it.representacion() % 4 == 0) {
                    var cantBlq = it.representacion() / 4
                    var rep = it.representacion()
                    for (i in 0..cantBlq-1) {
                        cont += 1
                        if (rep >= 4) {
                            rep -= 4
                            bloques.add(Triple(it.name(),cont,4))
                        } else if (rep < 4 && rep > 0) {
                            bloques.add(Triple(it.name(),cont,rep))
                            bloques.add(Triple(vacio,cont,4-rep))
                            rep = 0
                        } else if (rep == 0) {
                            bloques.add(Triple(vacio,cont,4-rep))
                        }
                    }
                } else if (it.representacion() / 4 == 0) {
                    var suma = 0
                    for (x in bloques) {
                        if (x.second == cont) {
                            suma += x.third
                        } 
                    }
                    if (suma < 4) {
                        var dif = 4-suma
                        if (it.representacion() <= dif) {
                            bloques.add(it.name(),cont,it.representacion())
                        } else {
                            cont += 1
                            bloques.add(it.name(),cont,it.representacion)
                        }
                    } else {
                        cont += 1
                        bloques.add(it.name(),cont,it.representacion) 
                    }
                } else if (it.representacion() / 4 != 0) {

                }
            }

        }

        return Triple(ocupacion,ali,desperdicio)
    }

    fun empaquetar() : Triple<Int,String,Int> {
        var ocupacion = 0
        var ali = ""
        var desperdicio = 0

        l.forEach {it ->
            ocupacion += it.representacion()
        }

        return Triple(ocupacion,ali,desperdicio)
    }

    fun reordenar() : Triple<Int,String,Int> {
        var ocupacion = 0
        var ali = ""
        var desperdicio = 0
    

    }

}