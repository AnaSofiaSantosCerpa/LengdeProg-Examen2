package lib.programas

public class FI(val n: Int) {
    var resultado = 0
    var a = 4
    var b = 5
    var mult = a * b

    init {
        
        if (0 <= n && n < mult) {
            resultado = n
        } else {
            for (i in 1..a) {
                resultado += subfi(n-b*i)
            }
        }

    }
    
    fun subfi(n: Int) : Int {

        if (0 <= n && n < mult) {
            resultado = n
        } else {
            resultado = FR(n).resultado
        }
        
        return resultado
    } 

}