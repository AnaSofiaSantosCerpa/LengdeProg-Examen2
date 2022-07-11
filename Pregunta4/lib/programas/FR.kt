package lib.programas

public class FR(val n: Int) {
    var resultado = 0
    var a = 4
    var b = 5
    var mult = a * b

    init {
        
        if (0 <= n && n < mult) {
            resultado = n
        } else {
            var i = 0
            resultado = subfr(n)
        }
    }

    fun subfr(n: Int) : Int {
        
        if (0 <= n && n < mult) {
            resultado = n

            return resultado
        } else {   
            resultado = subfr(n-b*1) + subfr(n-b*2) + subfr(n-b*3) + subfr(n-b*4)
        } 

        return resultado 
    }

}