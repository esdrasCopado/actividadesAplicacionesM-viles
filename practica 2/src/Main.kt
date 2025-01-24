//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
    println(verificaCadena("nombre")) // true
    println(verificaCadena("_nombre")) // true
    println(verificaCadena("_12")) // true
    println(verificaCadena("")) // false
    println(verificaCadena("012")) // false
    println(verificaCadena("no$")) // false
}

fun verificaCadena (cadena : String): Boolean {
    if (cadena.isEmpty()) {
        return false
    }

    if(!cadena[0].isLetter() && cadena[0]!='_'){
        return false
    }

    for(char in cadena){
        if(!char.isLetterOrDigit() && char!= '_'){
            return false
        }
    }

    return true
}