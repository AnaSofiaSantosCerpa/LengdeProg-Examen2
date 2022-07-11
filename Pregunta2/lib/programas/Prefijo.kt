package lib.programas

public class Prefijo(val op: MutableList<String>, val exp: MutableList<Boolean>) {
    var resultado = false
    var conj = "&"
    var disy = "|"
    var imp = "=>"
    var neg = "^"

    init {
        var newO = mutableListOf<String>()
        var newE = mutableListOf<Boolean>()
        var tieneNeg = true

        newO.addAll(op)
        newO = newO.reversed().toMutableList()
        newE.addAll(exp)

        //Se realizan los cambios en las expresiones según los 
        while (tieneNeg) {
            var negation = newO.indexOf(neg)
            if (negation == -1) {
                tieneNeg = false
            } else {
                changeExp(newO,newE,negation)
            }
        }

        //Se realizan las operaciones solo para disyunciones y conjunciones
        if (newO.contains(conj) || newO.contains(disy)) {
            var op1 = disCon(newO,newE)
            newO = op1.first
            newE = op1.second 
            println(newE)
        }


        //Se realizan las operacion para implicaciones
        if (newO.contains(imp)) {
            resultado = implicaciones(newO,newE)
        } else {
            resultado = newE[0]
        }
    }

    fun changeExp(o: MutableList<String>, e: MutableList<Boolean>, i:Int) {
        if (i != o.size-1) {
            e[i] = !e[i]
            o.remove(o[i])
        } else {
            e[e.size-1] = !e[e.size-1]
            o.remove(o[i])
        } 
    }

    fun disCon(o: MutableList<String>, e: MutableList<Boolean>) : Pair<MutableList<String>,MutableList<Boolean>> {
        var acumulado = false
        var salto = false
        var sizeO = o.size
        var nuevoE = mutableListOf<Boolean>()
        var nuevoO = mutableListOf<String>()


        for (i in 0..sizeO-1) {
            if (i == 0) {
                salto = true
            } 
            if (o[i] == imp) {
                nuevoE.add(acumulado)
                nuevoO.add(o[i])
                salto = true
            }

            if (salto) {
                if (o[i] == conj) {
                    acumulado = e[i] && e[i+1]
                    salto = false
                } else if (o[i] == disy) {
                    acumulado = e[i] || e[i+1]
                    salto = false
                } 
            } else {
                if (o[i] == conj) {
                    acumulado = acumulado && e[i+1]
                } else if (o[i] == disy) {
                    acumulado = acumulado || e[i+1]
                } 
            }

            if (i == sizeO-1) {
                nuevoE.add(acumulado)
            }
        }

        if (o[sizeO-1] == imp) {
            nuevoE.add(e[e.size-1])
        }

        if (!o.contains(imp)) {
            nuevoE.add(acumulado)
        }

        return Pair(nuevoO,nuevoE)
    }

    fun implicaciones(o: MutableList<String>, e: MutableList<Boolean>) : Boolean {
        var r = false
        var sizeO = o.size
        var n = sizeO - 1

        for (i in sizeO-1 downTo 0) {
            if (i == sizeO-1) {
                r = !e[i] || e[i+1]
            } else {
                r = !r || e[i]
            }
        }

        return r
    }
    
    fun mostrar() : String {
        var str = ""
        var listStr = mutableListOf<String>()
        var e = exp
        var o = op.reversed()
        var sizeO = o.size
        var sizeE = e.size

        
        for (i in 0..sizeO-1) {
            println(listStr)
            println(o[i])
            if (o[i] == neg && i == 0) {
                listStr.add(o[i])
                listStr.add(e[i].toString())
            } else {
                if (i == 0) {
                    listStr.add(e[i].toString())
                    listStr.add(o[i])
                } else {
                    if (o[i-1] != neg) {
                        println("aja")
                        listStr.add(e[i-1].toString())
                        listStr.add(o[i])
                    } else if (i != sizeO-1 && o[i+1] == neg) {
                        println("aqui")
                        listStr.add(o[i])
                        listStr.add(o[i+1])
                        listStr.add(e[i].toString())
                    } else if (i != sizeO-1 && o[i+1] != neg) {
                        println("ok")
                        listStr.add(o[i])
                        listStr.add(e[i].toString())
                    }
                }
            }
            
            if (i == sizeO-1) {
                listStr.add(e[sizeE-1].toString())
            } 
        } 

        var copy = mutableListOf<String>()
        var flag = mutableListOf<String>()

        for (i in 0..sizeO-1) {
            if (o[i] == imp) {
                flag.add(imp)
            }
        }
        while (!flag.isEmpty()) {
            copy.addAll(listStr)
            var sizeC = copy.size
            for (i in 0..sizeC-1) {
                if (copy[i] == imp) {
                    if (i-2 >= 0 && copy[i-2] != "(") {
                        if (copy[i-2] == neg && copy[i+1] == neg) {
                            listStr.add(i-2,"(")
                            listStr.add(i+4,")")
                            flag.remove(imp)
                        } else if (copy[i-2] == neg && copy[i+1] != neg) {
                            listStr.add(i-2,"(")
                            listStr.add(i+3,")")
                            flag.remove(imp)
                        } else if (copy[i-2] != neg && copy[i+1] == neg) {
                            listStr.add(i-1,"(")
                            listStr.add(i+4,")")
                            flag.remove(imp)
                        } else if (copy[i-2] != neg && copy[i+1] != neg) {
                            listStr.add(i-1,"(")
                            listStr.add(i+3,")")
                            flag.remove(imp)
                        }
                    } else if (i-2 < 0) {
                        if (copy[i+1] == neg) {
                            listStr.add(i-1,"(")
                            listStr.add(i+4,")")
                            flag.remove(imp)
                        } else {
                            listStr.add(i-1,"(")
                            listStr.add(i+3,")")
                            flag.remove(imp)
                        }
                    }
                }
            }
            copy.clear()
        }
        


             
        println(listStr)
        return str
    }
}