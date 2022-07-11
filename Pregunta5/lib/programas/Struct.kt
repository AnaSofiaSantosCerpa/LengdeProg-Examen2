package lib.programas

public class Struct(val n: String, val l: MutableList<String>, val at: MutableList<Atomico>) {
    var new = mutableListOf<Atomico>()
    var vacio = "vacio"


    init  {
        l.forEach {i ->
            var elem = at.find {it.n == i} 
            new.add(elem!!)
        }
    }


    fun name() : String {
        return n
    }

    fun listaTipos() : MutableList<String> {
        return l
    }

    fun sinEmpaquetar() : Triple<Int,Int,Int> {
        var ocupacion = 0
        var ali = 0
        var desperdicio = 0
        var bloques = mutableListOf<Triple<String,Int,Int>>()
        var cont = -1

        new.forEach {it ->

            //No hay tipo insertado
            if (bloques.isEmpty()) {
                //Es multiplo de 4
                if (it.alineacion() % 4 == 0) {
                    var cantBlq = it.alineacion() / 4
                    var rep = it.representacion()
                    for (i in 0..cantBlq-1) {
                        cont += 1

                        //se necesita un bloque entero
                        if (rep >= 4) {
                            rep -= 4
                            bloques.add(Triple(it.name(),cont,4))
                        //se necesita menos de un bloque
                        } else if (rep < 4 && rep > 0) {
                            bloques.add(Triple(it.name(),cont,rep))
                            bloques.add(Triple(vacio,cont,4-rep))
                            rep = 0
                        //se necesita un bloque más vacio
                        } else if (rep == 0) {
                            bloques.add(Triple(vacio,cont,4))
                        }
                    }
                //no es multiplo de 4
                } else {
                    var cantBlq = it.alineacion() / 4
                    if (cantBlq == 0) {
                        cont += 1
                        var dif = it.alineacion() - it.representacion()
                        if (dif == 0) {
                            bloques.add(Triple(it.name(),cont,it.representacion())) 
                        } else {
                            bloques.add(Triple(it.name(),cont,it.representacion())) 
                            bloques.add(Triple(vacio,cont,dif)) 
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
            //Se inserta el ultimo tipo
            } else if (it == new[new.size-1]) {
                var suma = 0
                for (x in bloques) {
                    if (x.second == cont) {
                        suma += x.third
                    } 
                } 
                //queda espacio en el bloque anterior
                if (suma < 4) {
                    var dife = 4-suma
                    //cabe en el bloque anterior
                    if (it.alineacion() <= dife) {
                        bloques.add(Triple(it.name(),cont,it.representacion()))
                    //no cabe en el bloque anterior
                    } else {
                        //se necesita un bloque o menos
                        if (it.alineacion() / 4 <= 1) {
                            bloques.add(Triple(vacio,cont,4-suma))
                            //se necesita un bloque
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
                                        rep = 0
                                    } 
                                }
                            //se necesita menos de un bloque
                            } else {
                                cont += 1
                                bloques.add(Triple(it.name(),cont,it.representacion())) 
                            }    
                        //se necesita más de un bloque
                        } else {
                            var byte = (cont+1) * 4 
                            //byte en multiplo
                            if (byte % it.alineacion() == 0) {
                                var cantBlq = it.alineacion() / 4
                                var rep = it.representacion()
                                for (i in 0..cantBlq-1) {
                                    cont += 1
                                    if (rep >= 4) {
                                        rep -= 4
                                        bloques.add(Triple(it.name(),cont,4))
                                    } else if (rep < 4 && rep > 0) {
                                        bloques.add(Triple(it.name(),cont,rep))
                                        rep = 0
                                    } 
                                }           
                            //byte no es multiplo
                            } else {
                                while (byte % it.alineacion() != 0) {
                                    cont += 1
                                    bloques.add(Triple(vacio,cont,4))
                                    byte = (cont+1) * 4
                                }
                                
                                var cantBlq = it.alineacion() / 4
                                var rep = it.representacion()
                                for (i in 0..cantBlq-1) {
                                    cont += 1
                                    if (rep >= 4) {
                                        rep -= 4
                                        bloques.add(Triple(it.name(),cont,4))
                                    } else if (rep < 4 && rep > 0) {
                                        bloques.add(Triple(it.name(),cont,rep))
                                        rep = 0
                                    } 
                                }
                            }
                        }
                    }
                //no queda espacio en el bloque anterior    
                } else {
                    //Si es multiplo de 4
                    if (it.alineacion() % 4 == 0) {
                        //si es 4
                        if (it.alineacion() / 4 == 1) {
                            var cantBlq = it.alineacion() / 4
                            var rep = it.representacion()
                            for (i in 0..cantBlq-1) {
                                cont += 1
                                if (rep >= 4) {
                                    rep -= 4
                                    bloques.add(Triple(it.name(),cont,4))
                                } else if (rep < 4 && rep > 0) {
                                    bloques.add(Triple(it.name(),cont,rep))
                                    rep = 0
                                } 
                            }
                        //si es mayor a 4
                        } else {
                            var byte = (cont+1) * 4 
                            //byte es multiplo
                            if (byte % it.alineacion() == 0) {
                                var cantBlq = it.alineacion() / 4
                                var rep = it.representacion()
                                for (i in 0..cantBlq-1) {
                                    cont += 1
                                    if (rep >= 4) {
                                        rep -= 4
                                        bloques.add(Triple(it.name(),cont,4))
                                    } else if (rep < 4 && rep > 0) {
                                        bloques.add(Triple(it.name(),cont,rep))
                                        rep = 0
                                    } 
                                }    
                            //byte no es multiplo          
                            } else {
                                while (byte % it.alineacion() != 0) {
                                    cont += 1
                                    bloques.add(Triple(vacio,cont,4))
                                    byte = (cont+1) * 4
                                }
                                
                                var cantBlq = it.alineacion() / 4
                                var rep = it.representacion()
                                for (i in 0..cantBlq-1) {
                                    cont += 1
                                    if (rep >= 4) {
                                        rep -= 4
                                        bloques.add(Triple(it.name(),cont,4))
                                    } else if (rep < 4 && rep > 0) {
                                        bloques.add(Triple(it.name(),cont,rep))
                                        rep = 0
                                    } 
                                }
                            }
                        }
                    //alineacion no es multiplo
                    } else {
                        var cantBlq = it.alineacion() / 4
                        //si es menor de 4
                        if (cantBlq == 0) {
                            cont += 1
                            bloques.add(Triple(it.name(),cont,it.representacion())) 
                        //si es mayor de 4
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
                                } 
                            }
                        }
                    }
                }
            //Se inserta otro tipo ni ultimo ni primero
            } else {  
                var suma = 0
                for (x in bloques) {
                    if (x.second == cont) {
                        suma += x.third
                    } 
                } 
                //queda espacio en el bloque anterior
                if (suma < 4) {
                    var dife = 4-suma
                    //cabe en el bloque anterior
                    if (it.alineacion() <= dife) {
                        bloques.add(Triple(it.name(),cont,it.representacion()))
                        //se necesita menos de espacio que alineacion
                        if (it.alineacion() != it.representacion()) {
                            var dif2 = 4 - (suma - it.representacion()) 
                            bloques.add(Triple(vacio,cont,dif2))
                        }
                    //no cabe en el bloque anterior
                    } else {
                        //se necesita un bloque o menos
                        if (it.alineacion() / 4 <= 1) {
                            bloques.add(Triple(vacio,cont,4-suma))
                            //se necesita un bloque
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
                            //se necesita menos de un bloque
                            } else {
                                cont += 1
                                var dif = it.alineacion() - it.representacion()
                                if (dif == 0) {
                                    bloques.add(Triple(it.name(),cont,it.representacion())) 
                                } else {
                                    bloques.add(Triple(it.name(),cont,it.representacion())) 
                                    bloques.add(Triple(vacio,cont,dif)) 
                                }
                            }    
                        //se necesita más de un bloque
                        } else {
                            var byte = (cont+1) * 4 
                            //byte en multiplo
                            if (byte % it.alineacion() == 0) {
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
                            //byte no es multiplo
                            } else {
                                while (byte % it.alineacion() != 0) {
                                    cont += 1
                                    bloques.add(Triple(vacio,cont,4))
                                    byte = (cont+1) * 4
                                }
                                
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
                            }
                        }
                    }
                //no queda espacio en el bloque anterior    
                } else {
                    //Si es multiplo de 4
                    if (it.alineacion() % 4 == 0) {
                        //si es 4
                        if (it.alineacion() / 4 == 1) {
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
                        //si es mayor a 4
                        } else {
                            var byte = (cont+1) * 4 
                            //byte es multiplo
                            if (byte % it.alineacion() == 0) {
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
                            //byte no es multiplo          
                            } else {
                                while (byte % it.alineacion() != 0) {
                                    cont += 1
                                    bloques.add(Triple(vacio,cont,4))
                                    byte = (cont+1) * 4
                                }
                                
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
                            }
                        }
                    //alineacion no es multiplo
                    } else {
                        var cantBlq = it.alineacion() / 4
                        //si es menor de 4
                        if (cantBlq == 0) {
                            cont += 1
                            var dif = it.alineacion() - it.representacion()
                            if (dif == 0) {
                                bloques.add(Triple(it.name(),cont,it.representacion())) 
                            } else {
                                bloques.add(Triple(it.name(),cont,it.representacion())) 
                                bloques.add(Triple(vacio,cont,dif)) 
                            }
                        //si es mayor de 4
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
                }    
            }
        }

        bloques.forEach {it ->
            ocupacion += it.third
            if (it.first == vacio) {
                desperdicio += it.third
            }
        }

        if (ocupacion % 4 == 0) {
            ali = ocupacion
        } else {
            ali = ((ocupacion / 4 ) + 1) * 4
        }

        return Triple(ocupacion,ali,desperdicio)
    }

    fun empaquetar() : Triple<Int,Int,Int> {
        var ocupacion = 0
        var ali = 0
        var desperdicio = 0

        new.forEach {it ->
            ocupacion += it.representacion()
        }

        if (ocupacion % 4 == 0) {
            ali = ocupacion
        } else {
            ali = ((ocupacion / 4) + 1 ) * 4
        }

        return Triple(ocupacion,ali,desperdicio)
    }

    fun reordenar() : Triple<Int,Int,Int> {
        var ocupacion = 0
        var ali = 0
        var desperdicio = 0
        var bloques = mutableListOf<Triple<String,Int,Int>>()
        var cont = -1
        var bandera = true


        new.forEach {it ->
            
            //No hay ningún tipo guardado
            if (bloques.isEmpty()) {
                //la alineacion del tipo es multiplo de 4
                if (it.alineacion() % 4 == 0) {
                    //se necesita 1 bloque
                    if (it.alineacion() / 4 == 1) {
                        cont += 1
                        //se guarda en el bloque entero
                        if (it.representacion() == it.alineacion()) {
                            bloques.add(Triple(it.name(),cont,it.representacion()))
                        //se guarda en menos del bloque
                        } else {
                            var dif = it.alineacion() - it.representacion()
                            bloques.add(Triple(it.name(),cont,it.representacion()))
                            bloques.add(Triple(vacio,cont,dif))
                        }
                    //se necesita mas de un bloque
                    } else {
                        var cantBlq = it.alineacion() / 4
                        var rep = it.representacion()
                        for (i in 0..cantBlq-1) {
                            cont += 1

                            //se necesita un bloque entero
                            if (rep >= 4) {
                                rep -= 4
                                bloques.add(Triple(it.name(),cont,4))
                            //se necesita menos de un bloque
                            } else if (rep < 4 && rep > 0) {
                                bloques.add(Triple(it.name(),cont,rep))
                                bloques.add(Triple(vacio,cont,4-rep))
                                rep = 0
                            } else if (rep == 0) {
                                bloques.add(Triple(vacio,cont,4))
                            }
                        }
                    }
                //se necesita menos de un bloque 
                } else {
                    cont += 1
                    //se guarda en todo el espacio que necesita alineacion
                    if (it.alineacion() == it.representacion()) {
                        bloques.add(Triple(it.name(),cont,it.representacion()))
                    //se guarda en menos espacio 
                    } else {
                        var dif = it.alineacion() - it.representacion()
                        bloques.add(Triple(it.name(),cont,it.representacion()))
                        bloques.add(Triple(vacio,cont,dif))
                    } 
                }
            //guardar los demás tipos
            } else {
                var n = it.name()
                var repeats = l.count {it == n}
                var flag = false
                var ingresado = bloques.count {it.first == n}

                //verificamos si no han sido guardados todos 
                if (repeats - ingresado != 0) {
                    flag = true
                } 

                if (flag) {
                    var suma = 0
                    for (x in bloques) {
                        if (x.second == cont) {
                            suma += x.third
                        } 
                    }
                    //queda espacio en el bloque anterior
                    if (suma < 4) {
                        var dife = 4-suma
                        //cabe en el bloque anterior
                        if (it.alineacion() <= dife) {
                            bloques.add(Triple(it.name(),cont,it.representacion()))
                            if (it.alineacion() - it.representacion() != 0) {
                                bloques.add(Triple(vacio,cont,it.alineacion() - it.representacion()))
                            }
                        //no cabe en el bloque anterior
                        } else {
                            var flag2 = false
                            var sub = new.subList(new.indexOf(it)+2,new.size)
                            var buscar = sub.find {it.a <= dife}
                            

                            //no se encontro uno que quepa 
                            if (buscar == null) {
                                flag2 = true
                            } else {
                                var n = buscar.name()
                                repeats = l.count {it == n}
                                ingresado = bloques.count {it.first == n}

                                //ya se colocaron todos los de ese tipo
                                if (repeats - ingresado == 0) {
                                    flag2 = true
                                } else {
                                    bloques.add(Triple(buscar.name(),cont,buscar.representacion()))
                                    if (buscar.alineacion() - buscar.representacion() != 0) {
                                        bloques.add(Triple(vacio,cont,buscar.alineacion() - buscar.representacion()))
                                    }
                                }            
                            }

                            if (flag2) {
                                //se necesita un bloque o menos
                                if (it.alineacion() / 4 <= 1) {
                                    bloques.add(Triple(vacio,cont,4-suma))
                                    //se necesita un bloque
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
                                            } 
                                        }
                                    //se necesita menos de un bloque
                                    } else {
                                        cont += 1
                                        bloques.add(Triple(it.name(),cont,it.representacion()))
                                        if (it.alineacion() - it.representacion() != 0) {
                                            bloques.add(Triple(vacio,cont,it.alineacion()-it.representacion()))
                                        } 
                                    }    
                                //se necesita más de un bloque
                                } else {
                                    var byte = (cont+1) * 4 
                                    //byte es multiplo
                                    if (byte % it.alineacion() == 0) {
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
                                                bloques.add(Triple(vacio,cont,4))
                                            }
                                        }           
                                    //byte no es multiplo
                                    } else {
                                        var flag3 = false
                                        buscar = new.find {byte % it.a == 0}

                                        if (buscar == null) {
                                            flag3 = true
                                        } else {
                                            repeats = l.count {it == buscar.n}
                                            ingresado = bloques.count {it.first == buscar.n}

                                            //ya se colocaron todos los de ese tipo
                                            if (repeats - ingresado == 0) {
                                                flag3 = true
                                            } else {
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
                                                        bloques.add(Triple(vacio,cont,4))
                                                    }
                                                }
                                            }            
                                        }

                                        if (flag3) {
                                            while (byte % it.alineacion() != 0) {
                                                cont += 1
                                                bloques.add(Triple(vacio,cont,4))
                                                byte = (cont+1) * 4
                                            }
                                            
                                            var cantBlq = it.alineacion() / 4
                                            var rep = it.representacion()
                                            for (i in 0..cantBlq-1) {
                                                cont += 1
                                                if (rep >= 4) {
                                                    rep -= 4
                                                    bloques.add(Triple(it.name(),cont,4))
                                                } else if (rep < 4 && rep > 0) {
                                                    bloques.add(Triple(it.name(),cont,rep))
                                                    bloques.add(Triple(it.name(),cont,4-rep))
                                                    rep = 0
                                                } else if (rep == 0) {
                                                    bloques.add(Triple(vacio,cont,4))
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    //no queda espacio en el bloque anterior    
                    } else {
                        //Si es multiplo de 4
                        if (it.alineacion() % 4 == 0) {
                            //si es 4
                            if (it.alineacion() / 4 == 1) {
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
                            //si es mayor a 4
                            } else {
                                var byte = (cont+1) * 4 
                                //byte es multiplo
                                if (byte % it.alineacion() == 0) {
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
                                //byte no es multiplo          
                                } else {
                                    var flag3 = false
                                    var buscar = new.find {byte % it.a == 0}

                                    if (buscar == null) {
                                        flag3 = true
                                    } else {
                                        var repeats = l.count {it == buscar.n}
                                        var ingresado = bloques.count {it.first == buscar.n}

                                        //ya se colocaron todos los de ese tipo
                                        if (repeats - ingresado != 0) {
                                            flag3 = true
                                        } else {
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
                                                    bloques.add(Triple(vacio,cont,4))
                                                }
                                            }
                                        }            
                                    }

                                    if (flag3) {
                                        while (byte % it.alineacion() != 0) {
                                            cont += 1
                                            bloques.add(Triple(vacio,cont,4))
                                            byte = (cont+1) * 4
                                        }
                                        
                                        var cantBlq = it.alineacion() / 4
                                        var rep = it.representacion()
                                        for (i in 0..cantBlq-1) {
                                            cont += 1
                                            if (rep >= 4) {
                                                rep -= 4
                                                bloques.add(Triple(it.name(),cont,4))
                                            } else if (rep < 4 && rep > 0) {
                                                bloques.add(Triple(it.name(),cont,rep))
                                                bloques.add(Triple(it.name(),cont,4-rep))
                                                rep = 0
                                            } else if (rep == 0) {
                                                bloques.add(Triple(vacio,cont,4))
                                            }
                                        }
                                    }
                                }
                            }
                        //alineacion no es multiplo
                        } else {
                            var cantBlq = it.alineacion() / 4
                            //si es menor de 4
                            if (cantBlq == 0) {
                                cont += 1
                                var dif = it.alineacion() - it.representacion()
                                if (dif == 0) {
                                    bloques.add(Triple(it.name(),cont,it.representacion())) 
                                } else {
                                    bloques.add(Triple(it.name(),cont,it.representacion())) 
                                    bloques.add(Triple(vacio,cont,dif)) 
                                }
                            }
                        }
                    }
                } 
            }
        }




        var ultimo = bloques[bloques.size-1].first

        while (ultimo == vacio) {
            bloques.removeLast()
            ultimo = bloques[bloques.size-1].first
        }


        bloques.forEach {it ->
            ocupacion += it.third
            if (it.first == vacio) {
                desperdicio += it.third
            }
        }

        if (ocupacion % 4 == 0) {
            ali = ocupacion
        } else {
            ali = ((ocupacion / 4) + 1 ) * 4
        }
        
        return Triple(ocupacion,ali,desperdicio)

    }

}