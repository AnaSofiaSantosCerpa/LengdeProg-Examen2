package lib.programas

public class Union(val n: String, val l: MutableList<String>, val at: MutableList<Atomico>, val st: MutableList<Struct>) {
    var atomicos =  mutableListOf<Atomico>()
    var structs  = mutableListOf<Struct>()
    var maxAt = Atomico("",0,0)

    init {
        l.forEach {x ->
            var buscar0 = st.find {it.n == x}
            var buscar1 = at.find {it.n == x}

            if (buscar1 != null) {
                atomicos.add(buscar1!!)
            }

            if (buscar0 != null) {
                structs.add(buscar0!!)
            }
        }

        atomicos.forEach {it ->
            if (it.alineacion() > maxAt.alineacion()) {
                maxAt = it
            }
        }
    }
    
    fun name() : String {
        return n
    }

    fun listaTipos() : MutableList<String> {
        return l
    }

    fun sinEmpaquetarU() : Triple<Int,Int,Int> {
        var ocupacion = 0
        var ali = 0
        var desperdicio = 0

        if (structs.isEmpty()) {
            ocupacion = maxAt.representacion()
            ali = maxAt.alineacion()
            desperdicio = ali - ocupacion
        } else {
            var maxSt = Struct("",mutableListOf(at[1].n),at)
            structs.forEach {it ->
                var itAli = it.sinEmpaquetar().second
                var maxStAli = maxSt.sinEmpaquetar().second
                if (itAli > maxStAli) {
                    maxSt = it
                }
            }

            if (atomicos.isEmpty()) {
                ocupacion = maxSt.sinEmpaquetar().first
                ali = maxSt.sinEmpaquetar().second
                desperdicio = maxSt.sinEmpaquetar().third
            } else {
                var maxStAli = maxSt.sinEmpaquetar().second
                if (maxAt.alineacion() >= maxStAli) {
                    ocupacion = maxAt.representacion()
                    ali = maxAt.alineacion()
                    desperdicio = ali - ocupacion
                } else {
                    ocupacion = maxSt.sinEmpaquetar().first
                    ali = maxSt.sinEmpaquetar().second
                    desperdicio = maxSt.sinEmpaquetar().third
                }
            }
        }

        return Triple(ocupacion,ali,desperdicio)
    }

    fun empaquetarU() : Triple<Int,Int,Int> {
        var ocupacion = 0
        var ali = 0
        var desperdicio = 0
        var maxSt = Struct("",mutableListOf(at[1].n),at)

        if (structs.isEmpty()) {
            ocupacion = maxAt.representacion()
            ali = maxAt.alineacion()
            desperdicio = ali - ocupacion
        } else {
            structs.forEach {it ->
                var itAli = it.empaquetar().second
                var maxStAli = maxSt.empaquetar().second
                if (itAli > maxStAli) {
                    maxSt = it
                }
            }

            if (atomicos.isEmpty()) {
                ocupacion = maxSt.empaquetar().first
                ali = maxSt.empaquetar().second
                desperdicio = maxSt.empaquetar().third
            } else {
                var maxStAli = maxSt.empaquetar().second
                if (maxAt.alineacion() >= maxStAli) {
                    ocupacion = maxAt.representacion()
                    ali = maxAt.alineacion()
                    desperdicio = ali - ocupacion
                } else {
                    ocupacion = maxSt.empaquetar().first
                    ali = maxSt.empaquetar().second
                    desperdicio = maxSt.empaquetar().third
                }
            }
        }

        return Triple(ocupacion,ali,desperdicio)
    }

    fun reordenarU() : Triple<Int,Int,Int> {
        var ocupacion = 0
        var ali = 0
        var desperdicio = 0
        var maxSt = Struct("",mutableListOf(at[1].n),at)

        if (structs.isEmpty()) {
            ocupacion = maxAt.representacion()
            ali = maxAt.alineacion()
            desperdicio = ali - ocupacion
        } else {
            structs.forEach {it ->
                var itAli = it.reordenar().second
                var maxStAli = maxSt.reordenar().second
                if (itAli > maxStAli) {
                    maxSt = it
                }
            }

            if (atomicos.isEmpty()) {
                ocupacion = maxSt.reordenar().first
                ali = maxSt.reordenar().second
                desperdicio = maxSt.reordenar().third
            } else {
                var maxStAli = maxSt.reordenar().second
                if (maxAt.alineacion() >= maxStAli) {
                    ocupacion = maxAt.representacion()
                    ali = maxAt.alineacion()
                    desperdicio = ali - ocupacion
                } else {
                    ocupacion = maxSt.reordenar().first
                    ali = maxSt.reordenar().second
                    desperdicio = maxSt.reordenar().third
                }
            }
        }

        return Triple(ocupacion,ali,desperdicio)
    }

}